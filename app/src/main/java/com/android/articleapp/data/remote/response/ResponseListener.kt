package com.android.articleapp.data.remote.response

import com.android.articleapp.data.model.Article

/****
 * ResponseListener callback which is responsible for giving the API response back to the presentation layer
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
interface ResponseListener {
    fun onSuccess(data: Article?)
    fun onFailure(message: String?)
}