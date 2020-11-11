package com.basri.mvvmandnotif.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.basri.mvvmandnotif.R
import com.basri.mvvmandnotif.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(object : Runnable {
            override fun run() {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            }
        }, 3000)
    }
}