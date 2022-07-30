package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View) {
        when {
            TextUtils.isEmpty(email_login.text.toString().trim() { it <= ' ' }) -> {
                Toast.makeText(this@LoginActivity, "Please enter your Email", Toast.LENGTH_LONG)
                    .show()

            }
            TextUtils.isEmpty(password_login.text.toString().trim() { it <= ' ' }) -> {
                Toast.makeText(this@LoginActivity, "Please Enter Your Password", Toast.LENGTH_LONG)
                    .show()
            }
            else -> {
                val email: String = email_login.text.toString().trim() { it <= ' ' }
                val password: String = password_login.text.toString().trim() { it <= ' ' }
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener({ task ->

                        if (task.isSuccessful) {
                            val firebaseuser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(this, "You are LoggedIn Sucessfully", Toast.LENGTH_LONG)
                                .show()


                            val intent =
                                Intent(this@LoginActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()
                        } else {
                            //If not registered sucessfully
                            Toast.makeText(
                                this@LoginActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
            }
        }
        fun register(view: View) {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}