package com.android.articleapp.view.fragment.articlelist

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import com.android.articleapp.BR
import com.android.articleapp.R
import com.android.articleapp.databinding.FragmentArticleListBinding
import com.android.articleapp.utils.NavigationCommand
import com.android.articleapp.utils.network.helper.Status
import com.android.articleapp.view.base.BaseFragment


/****
 * Login Fragment
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
class ArticleListFragment : BaseFragment<ArticleListViewModel, FragmentArticleListBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_article_list

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<ArticleListViewModel> {
        return ArticleListViewModel::class.java
    }

    override fun getTitle(): String {
        return getString(R.string.title_popular_title)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.callGetArticlesRequest()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun subscribeToNetworkLiveData() {
        viewModel.getArticlesResponse.observe(this, {

            viewModel.loadingStatus.value = Status.LOADING == it.status

            if (it.status == Status.SUCCESS) {
                it.data?.let { articleResponse -> viewModel.initView(articleResponse.results) }
            } else if (it.status == Status.ERROR)
                viewModel.message.set(getString(R.string.networkError))
        })
    }

    override fun subscribeToNavigationLiveData() {
        super.subscribeToNavigationLiveData()
        viewModel.articleClickEvent.observe(this, { article ->
            activity?.let {
                viewModel.resetFilter()
                viewModel.navigationCommands.value = NavigationCommand.To((ArticleListFragmentDirections.actionArticleListFragmentToArticleDetailFragment(article)))
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            )
        )

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (null == activity) return
        val searchView: SearchView
        activity!!.menuInflater.inflate(R.menu.search_main, menu)

        // Associate searchable configuration with the SearchView
        val searchManager =
            activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search)
            .actionView as SearchView
        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(activity!!.componentName)
        )
        searchView.setMaxWidth(Int.MAX_VALUE)

        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return filter(query)
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return filter(query)
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun filter(query: String?) : Boolean{
        query?.let { viewModel.filterItems(it) }
        return false
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }



}