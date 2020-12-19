package com.pack.memesapp.network

import com.pack.memesapp.network.models.MemeData
import retrofit2.Call
import retrofit2.http.GET

interface IMemeDataClient {
    @GET("memes")
    fun getMemes(): Call<List<MemeData>>
}