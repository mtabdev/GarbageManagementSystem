package com.example.wastemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_driver.*
import kotlinx.android.synthetic.main.activity_user.*

class Driver : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver)

        auth = FirebaseAuth.getInstance()

        btndriverlogin.setOnClickListener {
            checkcredentials()
        }
    }


    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {

            startActivity(Intent(this, driverDash::class.java))
            Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(baseContext, "Please Verify Your email", Toast.LENGTH_SHORT).show()
        }
    }

    //do later


    private fun checkcredentials() {
        if (driveremail.text.toString().isEmpty()) {
            driveremail.error = "Please Enter Email"
            driveremail.requestFocus()
            return
        }
        if (driverpass.text.toString().isEmpty()) {
            driverpass.error = "Please Enter Password"
            driverpass.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(driveremail.text.toString()).matches()) {
            driveremail.error = "Please Enter Email"
            driveremail.requestFocus()
            return
        }
        if (driverid.text.toString().isEmpty()) {
            driverid.error = "Enter driver ID"
            driverid.requestFocus()
            return
        }
        if (driverid.text.toString().equals("#CLEAN")) {

            auth.signInWithEmailAndPassword(driveremail.text.toString(), driverpass.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                        // ...
                    }

                    // ...
                }
        }
        else
        {
            Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT)
        }
    }
}