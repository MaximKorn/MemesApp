package com.pack.memesapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pack.memesapp.R
import com.pack.memesapp.activity.fragment.AddFragment
import com.pack.memesapp.activity.fragment.MemesListFragment
import com.pack.memesapp.activity.fragment.UserProfileFragment

class MainActivity : AppCompatActivity() {

    lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView = findViewById(R.id.bottomNavigationView)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContentFrame, MemesListFragment()).commit()

        navigationView.setOnNavigationItemSelectedListener {
            val selectedFragment = when (it.itemId) {
                R.id.action_memes_list -> MemesListFragment()
                R.id.action_add -> AddFragment()
                R.id.action_user_profile -> UserProfileFragment()
                else -> null
            }

            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContentFrame, selectedFragment).commit()
            }

            true
        }
    }
}