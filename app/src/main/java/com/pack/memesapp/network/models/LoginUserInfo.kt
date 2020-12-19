package com.pack.memesapp.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginUserInfo (

    @SerializedName("login")
    @Expose
    var login: String,

    @SerializedName("password")
    @Expose
    var password: String
    )