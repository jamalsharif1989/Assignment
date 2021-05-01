package com.android.articleapp.di.modules

import com.android.articleapp.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/****
 * The module which provides the android injection service to activities
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
@Suppress("unused")
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}