package com.pack.memesapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginUserInfo {

    @SerializedName("login")
    @Expose
    var login: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null
}