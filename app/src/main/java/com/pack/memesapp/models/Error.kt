package com.pack.memesapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Error {

    @SerializedName("code")
    @Expose
    var code: String? = null

    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String? = null
}