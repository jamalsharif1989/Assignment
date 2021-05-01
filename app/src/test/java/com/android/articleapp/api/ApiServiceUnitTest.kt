package com.android.articleapp.api

import com.android.articleapp.common.Configuration
import com.android.articleapp.data.remote.Api
import com.android.articleapp.data.remote.response.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class ApiServiceUnitTest {
    private var apiService: Api? = null

    @Before
    fun createService() {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(
            Configuration.CONNECTION_TIMEOUT,
            TimeUnit.MILLISECONDS
        )
        okHttpClient.readTimeout(
            Configuration.READ_TIMEOUT,
            TimeUnit.MILLISECONDS
        )
        okHttpClient.writeTimeout(
            Configuration.WRITE_TIMEOUT,
            TimeUnit.MILLISECONDS
        )
        okHttpClient.addInterceptor(RequestInterceptor())
        okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        apiService = Retrofit.Builder()
            .baseUrl(Configuration.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
            .create(Api::class.java)
    }

    @Test
    fun testServiceInitialization(){
        Assert.assertNotNull(apiService)
    }
}