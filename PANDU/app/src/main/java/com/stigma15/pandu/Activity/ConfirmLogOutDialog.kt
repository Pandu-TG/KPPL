package com.stigma15.pandu.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.activity_confirm_log_out_dialog.*

class ConfirmLogOutDialog : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_confirm_log_out_dialog)
        auth = Firebase.auth
        val currentUser = auth.currentUser

        btn_yesout.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            FirebaseAuth.getInstance().signOut()
            LoggedIn()
            finishAffinity()
        }
        btn_noout.setOnClickListener{
            finish()
        }

        //Hide status bar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21)
        {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
        if (Build.VERSION.SDK_INT >= 21)
        {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            getWindow().setStatusBarColor(Color.TRANSPARENT)
        }
    }
    private fun signOut() {
        auth.signOut()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@ConfirmLogOutDialog, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        googleSignInClient.signOut().addOnCompleteListener(this) {
        }
    }
    fun setWindowFlag(activity: Activity, bits:Int, on:Boolean) {
        val win = activity.getWindow()
        val winParams = win.getAttributes()
        if (on)
        {
            winParams.flags = winParams.flags or bits
        }
        else
        {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.setAttributes(winParams)
    }
    private fun LoggedIn(){
        val sharedPref = this.getSharedPreferences("Log in", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Logged in", false)
        editor.apply()
    }


}