package com.techguy.urbandictionary.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.techguy.urbandictionary.R
import com.techguy.urbandictionary.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {

    lateinit var mBinder: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize DataBinder
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        //Animate Logo
        YoYo.with(Techniques.Tada).duration(500).playOn(mBinder.logo)

        //Start MainActivity
        Handler(Looper.getMainLooper()).postDelayed(
            {
                finish()
                startActivity(Intent(this@Splash, MainActivity::class.java))
            },
            1500
        )

    }

}