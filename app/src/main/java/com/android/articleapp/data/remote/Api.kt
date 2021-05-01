package com.android.articleapp.data.remote

import androidx.lifecycle.LiveData
import com.android.articleapp.data.remote.response.ApiResponse
import com.android.articleapp.data.model.MostPopularArticleResponse
import retrofit2.http.GET
import retrofit2.http.Path

/****
 * Keep all the REST Apis here
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
interface Api {

    @GET("svc/mostpopular/v2/viewed/{index}.json")
    fun loadArticles(@Path("index") index: Int): LiveData<ApiResponse<MostPopularArticleResponse>>

}