package com.android.articleapp.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.articleapp.utils.NavigationCommand
import com.android.articleapp.utils.livedata.SingleLiveEvent

/****
 * Base view model. All the common implementation of viewmodel goes here
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
abstract class BaseViewModel : ViewModel() {

    val navigationCommands = SingleLiveEvent<NavigationCommand>()

    val loadingStatus: SingleLiveEvent<Boolean> = SingleLiveEvent()

    val backPressAction: SingleLiveEvent<Boolean> = SingleLiveEvent()

    val headerTitle: MutableLiveData<String> = MutableLiveData()


    fun setTitle(title: String) {
        headerTitle.value = title
    }

}