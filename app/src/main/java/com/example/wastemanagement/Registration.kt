package com.example.wastemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = FirebaseAuth.getInstance()

        AdminButton.setOnClickListener {
            signupUser()
        }

    }

    private fun signupUser() {
        if (AdminEmail.text.toString().isEmpty()) {
            AdminEmail.error = "Please Enter Email"
            AdminEmail.requestFocus()
            return
        }
        if (AdminPassword.text.toString().isEmpty()) {
            AdminPassword.error = "Please Enter Password"
            AdminPassword.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(AdminEmail.text.toString()).matches()) {
            AdminEmail.error = "Please Enter Email"
            AdminEmail.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(
            AdminEmail.text.toString(),
            AdminPassword.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, User::class.java))
                                Toast.makeText(
                                    baseContext, "Successfully Registered.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            } else {
                                Toast.makeText(
                                    baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }


                        }

                }
            }
    }
}

