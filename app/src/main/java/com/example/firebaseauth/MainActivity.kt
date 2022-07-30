package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user=intent.getStringExtra("user_id")
        val email=intent.getStringExtra("email_id")

        user_show.text="User Id::$user"
        email_show.text="Email ID::$email"

        btn_logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            finish()
        }
    }
}