package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val logedt=findViewById<EditText>(R.id.email_reg)
    }

    fun login_reg(view: View) {
        //Login main thing is implemented over here
        when{
            TextUtils.isEmpty(email_reg.text.toString().trim(){it <=' '})->{
                Toast.makeText(this@RegisterActivity,"Please enter your Email",Toast.LENGTH_LONG).show()

            }
            TextUtils.isEmpty(password_reg.text.toString().trim(){it<=' '})->{
                Toast.makeText(this@RegisterActivity,"Please Enter Your Password",Toast.LENGTH_LONG).show()
            }
            else->{
               val email:String=email_reg.text.toString().trim(){it<=' '}
                val password:String=password_reg.text.toString().trim(){it<=' '}
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener( { task ->

                    if (task.isSuccessful) {
                        val firebaseuser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(this, "You are registered Sucessfully", Toast.LENGTH_LONG)
                            .show()


                        val intent =
                            Intent(this@RegisterActivity, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("user_id", firebaseuser.uid)
                        intent.putExtra("email_id", email)
                        startActivity(intent)
                        finish()
                    }else{
                        //If not registered sucessfully
                        Toast.makeText(this@RegisterActivity,
                        task.exception!!.message.toString(),
                        Toast.LENGTH_LONG
                        ).show()
                    }
                })
            }
        }
    }
}