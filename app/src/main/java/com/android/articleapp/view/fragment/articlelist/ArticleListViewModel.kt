package com.android.articleapp.view.fragment.articlelist

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.articleapp.R
import com.android.articleapp.BR
import com.android.articleapp.common.Configuration
import com.android.articleapp.data.model.Article
import com.android.articleapp.data.model.MostPopularArticleResponse
import com.android.articleapp.data.repository.ArticleRepository
import com.android.articleapp.utils.livedata.AbsentLiveData
import com.android.articleapp.utils.livedata.SingleLiveEvent
import com.android.articleapp.utils.network.helper.Resource
import com.android.articleapp.view.listeners.OnItemClickListener
import com.android.articleapp.view.base.BaseViewModel
import javax.inject.Inject
import me.tatarka.bindingcollectionadapter2.ItemBinding

/****
 * The presentation layer of Login fragment
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
class ArticleListViewModel @Inject constructor(private val articleRepository: ArticleRepository) : BaseViewModel(){

    val message = ObservableField<String>()
    val articleClickEvent = SingleLiveEvent<Article>()
    var articlesList : List<Article> = ArrayList()

    private val getArticlesRequest=MutableLiveData<Int>()
    val getArticlesResponse: LiveData<Resource<MostPopularArticleResponse>> = Transformations
        .switchMap(getArticlesRequest) { index ->
            if (index <= 0) {
                AbsentLiveData.create()
            } else {
                articleRepository.loadArticles(index)
            }
        }


    var items = ObservableArrayList<Article>()
    var itemBinding: ItemBinding<Article> =
        ItemBinding.of<Article>(BR.article, R.layout.item_article_list)
            .bindExtra(BR.listener, object : OnItemClickListener {
                override fun onItemClicked(item: Any) {
                    articleClickEvent.value = item as Article
                }
            })

    fun initView(results: List<Article>) {
        items.clear()
        articlesList = results
        articlesList.forEach{article -> items.add(article)}
    }

    fun filterItems(searchStr : String){
        items.clear()
        articlesList.forEach { if(it.title.contains(searchStr, true)) items.add(it) }
    }

    fun resetFilter(){
        items.clear()
        articlesList.forEach{article -> items.add(article)}
    }

    fun callGetArticlesRequest(){
        if(items.isEmpty())
            getArticlesRequest.value =  Configuration.ARTICLE_DATA_VARIABLE
    }

}