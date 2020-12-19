package com.pack.memesapp.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity
import com.pack.memesapp.R
import com.pack.memesapp.activity.auth.AuthActivity

class SplashActivity : AppCompatActivity() {

   // private val splashDelay: Long = this.resources.getInteger(R.integer.splash_delay).toLong()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({ startActivity(Intent(this, AuthActivity::class.java)) }, 300)
    }
}