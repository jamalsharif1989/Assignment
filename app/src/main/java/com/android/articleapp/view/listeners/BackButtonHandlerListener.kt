package com.android.articleapp.view.listeners

/****
 * Back button handler interface. Add/remove listener functionality
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
interface BackButtonHandlerListener {
    fun addBackpressListener(listner: BackPressListener)
    fun removeBackpressListener(listner: BackPressListener)
}