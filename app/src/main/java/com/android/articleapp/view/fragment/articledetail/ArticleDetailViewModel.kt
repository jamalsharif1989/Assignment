package com.android.articleapp.view.fragment.articledetail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.android.articleapp.data.model.Article
import com.android.articleapp.data.remote.response.ResponseListener
import com.android.articleapp.data.repository.ArticleRepository
import com.android.articleapp.utils.livedata.SingleLiveEvent
import com.android.articleapp.view.base.BaseViewModel
import javax.inject.Inject

class ArticleDetailViewModel @Inject constructor(private val articleRepository: ArticleRepository) :
    BaseViewModel() {
    val article = ObservableField<Article>()
    val content = ObservableField<String>()

    private val articleMutableLiveData = MutableLiveData<Article>()

    private val errorMessage = SingleLiveEvent<Void>()

    fun setArticle(article: Article) {
        this.article.set(article)
    }

    fun loadArticleDetails() {
        loadingStatus.value = true
        articleRepository.loadArticleDetails(article.get(), object : ResponseListener {
            override
            fun onSuccess(data: Article?) {
                loadingStatus.value = false
                articleMutableLiveData.setValue(data)
            }

            override
            fun onFailure(message: String?) {
                loadingStatus.value = false
                errorMessage.call()
            }
        })
    }

    fun getArticleMutableLiveData() : MutableLiveData<Article>{
        return articleMutableLiveData
    }

    fun getErrorMessage() : SingleLiveEvent<Void>{
        return errorMessage
    }
}