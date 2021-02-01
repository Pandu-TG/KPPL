package com.stigma15.pandu.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.activity_verification.*

class VerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        verify_button.setOnClickListener {
            Toast.makeText(this, "Verifikasi Berhasil", Toast.LENGTH_SHORT).show()
            val intent = Intent (this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
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
}