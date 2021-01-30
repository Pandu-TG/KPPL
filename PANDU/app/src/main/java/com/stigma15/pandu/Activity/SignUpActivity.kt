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
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.stigma15.pandu.Activity.helper.RESULT_ADD
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.activity_login2.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.reginputEmail

class SignUpActivity : AppCompatActivity() {
    // menginisiasikan firestore
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private var isEdit = false

    companion object {
        private const val checkBox2 = "chbx"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            getWindow().setStatusBarColor(Color.TRANSPARENT)
        }

        LogIn.setOnClickListener { onBackPressed() }

        SignUp_button.setOnClickListener {
            val email = reginputEmail.text.toString()
            val password = reginputpw.text.toString()
            val repassword = retype_pw.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Mohon untuk mengisi semua data yang diperluhkan",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else if (password != repassword) {
                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (!checkBox2.isChecked) {
                Toast.makeText(this, "Mohon setujui syarat pemakaian", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        return@addOnCompleteListener
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else
                        Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, VerificationActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Log.d("Main", "Registrasi Gagal : ${it.message}")
                    Toast.makeText(this, "Data yang diperluhkan tidak sesuai", Toast.LENGTH_SHORT)
                        .show()
                }
        }

    }

    /**
    private fun Register() {
        val usrname = reginputusername.text.toString().trim()
        val noha = inputnohp.text.toString().trim()
        if (title.isEmpty()) {
            reginputusername.error = "Field can not be blank"
            return
        }
        if (isEdit) {

        } else {
            val currentUser = auth.currentUser
            val user = hashMapOf(
                "uid" to currentUser?.uid,
                "title" to usrname,
                "description" to noha
            )
            firestore.collection("accdata")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(
                        this,
                        "Sucses with ID : ${documentReference.id}",
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(RESULT_ADD, intent)
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error adding document", Toast.LENGTH_SHORT).show()
                }

        }
    } **/


    fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win = activity.getWindow()
        val winParams = win.getAttributes()
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.setAttributes(winParams)
    }
}
