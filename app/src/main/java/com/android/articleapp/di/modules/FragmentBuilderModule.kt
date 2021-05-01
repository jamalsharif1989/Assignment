package com.android.articleapp.di.modules

import com.android.articleapp.view.fragment.articledetail.ArticleDetailFragment
import com.android.articleapp.view.fragment.articlelist.ArticleListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/****
 * The module which provides the android injection service to fragments.
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
@Suppress("unused")
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeArticleListFragment(): ArticleListFragment

    @ContributesAndroidInjector
    abstract fun contributeArticleDetailFragment(): ArticleDetailFragment
}