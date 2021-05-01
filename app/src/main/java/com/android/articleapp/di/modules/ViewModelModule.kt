package com.android.articleapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.articleapp.di.viewmodel.ViewModelFactory
import com.android.articleapp.di.key.ViewModelKey
import com.android.articleapp.view.activity.MainViewModel
import com.android.articleapp.view.fragment.articledetail.ArticleDetailViewModel
import com.android.articleapp.view.fragment.articlelist.ArticleListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/****
 * View Model module which provides the viewmodelfactory and viewmodel instances
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticleListViewModel::class)
    fun bindArticleListViewModel(articleListViewModel: ArticleListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleDetailViewModel::class)
    fun bindArticleDetailViewModel(articleDetailViewModel: ArticleDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}