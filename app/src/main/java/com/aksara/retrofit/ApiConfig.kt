package com.aksara.retrofit

import com.aksara.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiConfig {
    fun getApiService(): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val apiUrl = BuildConfig.BASE_URL
        val retrofit =
            Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
        return retrofit.create(ApiService::class.java)
    }
}