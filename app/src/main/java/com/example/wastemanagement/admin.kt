package com.example.wastemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin.*

class admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        AdminButton.setOnClickListener {
            validateAdmin()
        }
    }
    fun validateAdmin()
    {
        if(AdminEmail.text.toString().equals("admin") && AdminPassword.text.toString().equals("admin"))
        {
         val intent=Intent(this,adminHomePage::class.java)
            startActivity(intent)
        }
    }
}