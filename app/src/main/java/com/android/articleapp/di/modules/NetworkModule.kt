package com.android.articleapp.di.modules

import com.android.articleapp.common.Configuration
import com.android.articleapp.data.remote.response.RequestInterceptor
import com.android.articleapp.utils.network.adapter.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/****
 * Network Module which provides the retrofit related instances
 * Author: Jamal
 * Company: Article App
 * Created on: 05/01/21
 * Modified on: 05/01/21
 *****/

@Module
class NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val cookieHandler = CookieManager()
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(RequestInterceptor())
            .cookieJar(JavaNetCookieJar(cookieHandler))
            .connectTimeout(Configuration.CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(Configuration.READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(Configuration.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Configuration.BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}