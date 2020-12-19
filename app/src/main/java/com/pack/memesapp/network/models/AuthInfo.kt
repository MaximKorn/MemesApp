package com.pack.memesapp.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthInfo (

    @SerializedName("accessToken")
    @Expose
    var accessToken: String,

    @SerializedName("userInfo")
    @Expose
    var userInfo: UserInfo
    )