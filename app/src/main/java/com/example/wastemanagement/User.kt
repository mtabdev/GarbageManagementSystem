package com.example.wastemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_user.*

class User : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        auth = FirebaseAuth.getInstance()
        UserLoginButton.setOnClickListener {
            checkUser()
        }
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(currentUser : FirebaseUser?)
    {
        if(currentUser != null)
        {
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, userPage::class.java))
                Toast.makeText(this,"Logged In Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Toast.makeText(baseContext , "Please Verify Your email" , Toast.LENGTH_SHORT).show()
            }
        }

        //do later
    }


    private fun checkUser() {
        if (UserEmailId.text.toString().isEmpty()) {
            UserEmailId.error = "Please Enter Email"
            UserEmailId.requestFocus()
            return
        }
        if (UserPassword.text.toString().isEmpty()) {
            UserPassword.error = "Please Enter Password"
            UserPassword.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(UserEmailId.text.toString()).matches()) {
            UserEmailId.error = "Please Enter Email"
            UserEmailId.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(UserEmailId.text.toString(), UserPassword.text.toString())
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
}