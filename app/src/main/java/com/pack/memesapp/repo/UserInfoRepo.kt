package com.pack.memesapp.repo

import android.content.Context
import android.content.SharedPreferences
import com.pack.memesapp.network.models.AuthInfo
import com.pack.memesapp.repo.models.UserInfo


class UserInfoRepo (private val context: Context) {

    val  INFO_KEY = "memes"
    val  ACCESS_TOKEN = "accessToken"
    val  ID = "userId"
    val  USERNAME = "username"
    val  FIRST_NAME = "firstName"
    val  LAST_NAME = "lastName"
    val  USER_DESCRIPTION = "userDescription"

    fun saveUserData(data: AuthInfo) {
        val userSharedPreferences: SharedPreferences =
            context.getSharedPreferences(INFO_KEY, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = userSharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, data.accessToken)
        editor.putInt(ID, data.userInfo!!.id)
        editor.putString(USERNAME, data.userInfo!!.username)
        editor.putString(FIRST_NAME, data.userInfo!!.firstName)
        editor.putString(LAST_NAME, data.userInfo!!.lastName)
        editor.putString(USER_DESCRIPTION, data.userInfo!!.userDescription)
        editor.apply()
    }

    fun getUserInfo(): UserInfo? {
        val userSharedPreferences: SharedPreferences =
            context.getSharedPreferences(INFO_KEY, Context.MODE_PRIVATE)
        if (!userSharedPreferences.contains(ACCESS_TOKEN)) {
            return null
        }

        val userInfo = UserInfo()
        userInfo.accessToken = userSharedPreferences.getString(ACCESS_TOKEN, "")
        userInfo.id = userSharedPreferences.getInt(ID, 0)
        userInfo.username = userSharedPreferences.getString(USERNAME, "")
        userInfo.firstName = userSharedPreferences.getString(FIRST_NAME, "")
        userInfo.lastName = userSharedPreferences.getString(LAST_NAME, "")
        userInfo.userDescription = userSharedPreferences.getString(USER_DESCRIPTION, "")

        return userInfo
    }
}