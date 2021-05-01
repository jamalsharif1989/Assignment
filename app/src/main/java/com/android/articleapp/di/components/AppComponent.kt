package com.android.articleapp.di.components

import android.app.Application
import com.android.articleapp.ArticleApplication
import com.android.articleapp.di.modules.ActivityBuilderModule
import com.android.articleapp.di.modules.AppModule
import com.android.articleapp.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/****
 * Application Component
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBuilderModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(articleApplication: ArticleApplication)
}