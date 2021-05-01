package com.android.articleapp.view.listeners

/****
 * Back press listener for handling back navigation in activity/fragments
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
interface BackPressListener {
    fun onBackPress(): Boolean
}