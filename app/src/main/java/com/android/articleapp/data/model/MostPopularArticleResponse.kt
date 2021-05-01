package com.android.articleapp.data.model

import com.google.gson.annotations.SerializedName

data class MostPopularArticleResponse(@SerializedName("status") val status : String,
@SerializedName("copyright") val copyright : String,
@SerializedName("num_results") val numResults : Int,
@SerializedName("results") val results : List<Article>
)