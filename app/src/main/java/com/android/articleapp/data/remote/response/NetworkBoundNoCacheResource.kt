package com.android.articleapp.data.remote.response

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.android.articleapp.utils.network.helper.Resource
import com.google.gson.Gson
import io.reactivex.annotations.NonNull


abstract class NetworkBoundNoCacheResource<ResultType>  {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {

        val apiResponse = loadFromNetwork()

        result.addSource(apiResponse) {response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    result.setValue(Resource.success(processResponse(response)))
                }
                is ApiErrorResponse -> {
                    result.setValue(Resource.error(response.error.message, null))
                }
            }
        }
    }

    @NonNull
    @MainThread
    protected abstract fun loadFromNetwork(): LiveData<ApiResponse<ResultType>>

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    /**
     * @param outputClassType: The expected model class in the output Resource
     * @return LiveData with outputClassType model Resource
     */
    fun <T> asLiveData(outputClassType: Class<T>): LiveData<Resource<T>> {

        return Transformations.map(result) { input ->
            var outputData: T? = null
            if (input?.data != null) {
                outputData = Gson().fromJson(input.data.toString(), outputClassType)
            }
            Resource(input.status, outputData, input.error)
        }
    }

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<ResultType>) = response.body

}