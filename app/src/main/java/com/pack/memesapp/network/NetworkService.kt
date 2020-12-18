package com.pack.memesapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetworkService {

    private const val BASE_URL = "https://r2.mocker.surfstudio.ru/android_vsu/"
    private var mRetrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)

        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    val authClient = mRetrofit.create<IAuthClient>()
    val memeDataClient = mRetrofit.create<IMemeDataClient>()
}