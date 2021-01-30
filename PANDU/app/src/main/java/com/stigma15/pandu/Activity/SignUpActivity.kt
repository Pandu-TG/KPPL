package com.stigma15.pandu.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.activity_login2.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.reginputEmail

class SignUpActivity : AppCompatActivity(){

    companion object{
        private const val checkBox2 = "chbx"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

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

        LogIn.setOnClickListener { onBackPressed() }
        SignUp_button.setOnClickListener{
            val username = reginputusername.text.toString()
            val email = reginputEmail.text.toString()
            val password = reginputpw.text.toString()
            val repassword = retype_pw.text.toString()
            val nohp = inputnohp.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || nohp.isEmpty()){
                Toast.makeText(this, "Mohon untuk mengisi semua data yang diperluhkan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (password != repassword){
                Toast.makeText(this, "Password tidak sama",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (!checkBox2.isChecked){
                Toast.makeText(this, "Mohon setujui syarat pemakaian", Toast.LENGTH_SHORT ).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{
                    if (!it.isSuccessful){ return@addOnCompleteListener
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else
                        Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                    val intent= Intent (this, LoginActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener{
                    Log.d("Main", "Registrasi Gagal : ${it.message}")
                    Toast.makeText(this, "Data yang diperluhkan tidak sesuai", Toast.LENGTH_SHORT).show()
                }
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