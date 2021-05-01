package com.android.articleapp.view.fragment.articledetail

import android.os.Bundle
import com.android.articleapp.BR
import com.android.articleapp.R
import com.android.articleapp.common.Constants
import com.android.articleapp.databinding.FragmentArticleDetailBinding
import com.android.articleapp.view.base.BaseFragment


class ArticleDetailFragment : BaseFragment<ArticleDetailViewModel, FragmentArticleDetailBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_article_detail

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<ArticleDetailViewModel> {
        return ArticleDetailViewModel::class.java
    }

    override fun getTitle(): String {
        return getString(R.string.title_popular_title)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.takeIf { args -> args.containsKey(Constants.KEY_ARTICLE) }?.let {
            viewModel.setArticle(it.getParcelable(Constants.KEY_ARTICLE))
            viewModel.loadArticleDetails()
        }
    }

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()
        viewModel.getArticleMutableLiveData().observe(this, { article ->
            article?.let {
                viewModel.content.set(it.content)

            }
        })

        viewModel.getErrorMessage().observe(this, { _ ->
            viewModel.content.set(activity?.getString(R.string.networkError))
            viewModel.loadingStatus.value = false
        })
    }

}