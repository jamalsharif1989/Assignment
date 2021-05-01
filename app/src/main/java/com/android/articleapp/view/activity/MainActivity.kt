package com.android.articleapp.view.activity

import com.android.articleapp.BR
import com.android.articleapp.R
import com.android.articleapp.databinding.ActivityMainBinding
import com.android.articleapp.view.base.BaseActivity
import com.android.articleapp.view.fragment.articlelist.ArticleListViewModel


/****
 * MainActivity class
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
class MainActivity : BaseActivity<ArticleListViewModel, ActivityMainBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_main

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<ArticleListViewModel> {
      return ArticleListViewModel::class.java
    }
}
