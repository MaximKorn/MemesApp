package com.pack.memesapp.network

import com.pack.memesapp.models.AuthInfo
import com.pack.memesapp.models.LoginUserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthClient {

    @POST("auth/login")
    fun login(@Body data: LoginUserInfo): Call<AuthInfo?>?
}