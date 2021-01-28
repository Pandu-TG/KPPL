package com.stigma15.pandu.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        auth = Firebase.auth
        profile_close_btn.setOnClickListener{val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        }

        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnLogOut.setOnClickListener {
            val intent= Intent(this@ProfileActivity, ConfirmLogOutDialog::class.java)
            startActivity(intent)

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

    private fun updateUI(currentUser: FirebaseUser) {
        currentUser?.let {
            val username = currentUser.displayName
            val phoneNumber = currentUser.phoneNumber
            val email = currentUser.email
            val photoUrl = currentUser.photoUrl
            val emailVerified = currentUser.isEmailVerified
            val uid = currentUser.uid
            nama_profile.text = username
            if (TextUtils.isEmpty(username)) {
                nama_profile.text = "User"
            }
            call_username.text = username
            if (TextUtils.isEmpty(username)) {
                call_username.text = "No Name"
            }
            text_email.text = email
            for (profile in it.providerData) {
                val providerId = profile.providerId
                if (providerId == "password" && emailVerified == true) {
                    btnEmailVerify.isVisible = false
                }
                if (providerId == "phone") {
                    call_phone.text = phoneNumber
                    call_phone.text = providerId
                }
            }
        }
        /**
        // menampilkan foto profile gmail ( jika login menggunakan gmail, jika tidak maka menampilkan default icon )
        binding.ivImage.setImageURI(photoUrl)
        if(TextUtils.isEmpty(name)){
        binding.ivImage.setImageURI(photoUrl)
        }else
        Picasso.with(this@MainActivity).load(photoUrl).into(ivImage)
        } **/

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

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else{
            updateUI(currentUser)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}