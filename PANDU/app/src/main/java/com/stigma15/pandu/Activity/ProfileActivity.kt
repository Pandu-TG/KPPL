package com.stigma15.pandu.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.with
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
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
        btnEmailVerify.setOnClickListener{
            sendEmailVerify()
        }
        Logout.setOnClickListener {
            val intent= Intent(this@ProfileActivity, ConfirmLogOutDialog::class.java)
            startActivity(intent)
        }
        profile_close_btn.setOnClickListener{
            onBackPressed()
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
            call_name.text = username
            if (TextUtils.isEmpty(username)) {
                call_name.text = "No Name"
            }
            call_email.text = email
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
            photo_profile.setImageURI(photoUrl)
            if(TextUtils.isEmpty(username)){
                photo_profile.setImageURI(photoUrl)
            }else
                Glide.with(this)
                    .load(photoUrl)
                    .circleCrop()
                    .into(photo_profile)
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
    // untuk verifikasi email
    private fun sendEmailVerify() {
        btnEmailVerify.isEnabled = false
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                btnEmailVerify.isEnabled = true
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        "Verification email sent to ${user.email} ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}