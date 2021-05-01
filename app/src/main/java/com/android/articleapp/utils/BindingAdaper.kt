package com.android.articleapp.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.android.articleapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["loadArticleImage", "isCircle"], requireAll = false)
    fun bindImage(imgView: ImageView, url: String?, isCircle:Boolean = false) {

        loadImage(imgView, url, isCircle)
    }

    private fun loadImage(imgView: ImageView, imgUrl: String?, isCircle: Boolean) {
        imgUrl?.let {
            val imgUri = Uri.parse(imgUrl).buildUpon().scheme("https").build()

            Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                    (if(isCircle) RequestOptions().circleCrop() else RequestOptions())
                        .placeholder(if(isCircle) R.drawable.ic_circle_article else R.drawable.ic_article)
                        .error(if(isCircle) R.drawable.ic_circle_article else R.drawable.ic_article)
                )
                .into(imgView)
        }
    }
}