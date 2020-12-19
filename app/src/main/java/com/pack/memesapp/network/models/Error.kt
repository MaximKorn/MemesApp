package com.pack.memesapp.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Error (

    @SerializedName("code")
    @Expose
    var code: String,

    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String
    )