package com.pack.memesapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthInfo {

    @SerializedName("accessToken")
    @Expose
    var accessToken: String? = null

    @SerializedName("userInfo")
    @Expose
    var userInfo: UserInfo? = null
}