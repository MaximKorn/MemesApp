package com.pack.memesapp.network

import com.pack.memesapp.network.models.AuthInfo
import com.pack.memesapp.network.models.LoginUserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthClient {

    @POST("auth/login")
    fun login(@Body data: LoginUserInfo): Call<AuthInfo?>?
}