package com.android.articleapp.data.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.android.articleapp.data.remote.Api
import com.android.articleapp.data.model.Article
import com.android.articleapp.data.remote.response.ApiResponse
import com.android.articleapp.data.remote.response.ResponseListener
import com.android.articleapp.data.model.MostPopularArticleResponse
import com.android.articleapp.data.remote.response.NetworkBoundNoCacheResource
import com.android.articleapp.utils.network.helper.Resource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

/****
 * Article Repository which keeps all the service calls related to Article entity
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
class ArticleRepository @Inject constructor(val apiService: Api) : BaseRepository() {
    /**
     * The method to load popular articles listing
     * @param days : days
     */
    fun loadArticles(days: Int): LiveData<Resource<MostPopularArticleResponse>> {
        return object : NetworkBoundNoCacheResource<MostPopularArticleResponse>() {
            override fun loadFromNetwork(): LiveData<ApiResponse<MostPopularArticleResponse>> {
                return apiService.loadArticles(days)
            }
        }.asLiveData()
    }

    /**
     * This method get html and parse required data
     * @param article article item
     * @param responseListener callback
     */
    @SuppressLint("CheckResult")
    fun loadArticleDetails(
        article: Article?,
        responseListener: ResponseListener
    ) {
        Observable.fromCallable {
            val document: Document = Jsoup.connect(article?.url).get()
            article?.content = document.select("p").text()
            false
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    responseListener.onSuccess(
                        article
                    )
                }
            ) { error: Throwable ->
                responseListener.onFailure(
                    error.message
                )
            }
    }
}