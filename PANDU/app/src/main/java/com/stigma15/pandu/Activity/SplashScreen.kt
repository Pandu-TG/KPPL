package com.stigma15.pandu.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        iv_note.alpha = 0f
        iv_note.animate().setDuration(1000).alpha(1f).withEndAction{
            if(onBoardingFinished()){
                if(LoggedIn()) {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                }
                else{
                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                }
            }
            else{
                val i = Intent(this, OnboardingActivity::class.java)
                startActivity(i)
            }
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

    }
    private fun onBoardingFinished(): Boolean {
        val sharedPref = this@SplashScreen.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
    private fun LoggedIn(): Boolean {
        val sharedPref = this@SplashScreen.getSharedPreferences("Log in", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Logged in", false)
    }
}