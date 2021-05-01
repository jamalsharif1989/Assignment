package com.android.articleapp.di.modules

import com.android.articleapp.data.remote.Api
import com.android.articleapp.data.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/****
 * Application Module which is responsible for providing the app wide instances.
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/
@Module(includes = [(ViewModelModule::class)])
class AppModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : Api {
        return retrofit.create(Api::class.java)
    }


    @Provides
    @Singleton
    fun provideArticleRepository(api: Api): ArticleRepository {
        return ArticleRepository(api)
    }

}