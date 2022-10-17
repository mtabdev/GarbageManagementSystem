package com.example.wastemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_driveradmin.*
import java.lang.Exception


class createDriveradmin : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var username:EditText
    lateinit var password:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_driveradmin)
        auth= FirebaseAuth.getInstance()
        username=findViewById(R.id.andmincreatedriveremail)
        password=findViewById(R.id.admincreatedriverpassword)
        btncreatedriveradmin.setOnClickListener {
            registerdriver()

        }
    }



    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

    }
    private fun registerdriver()
    {
        var email=username.text.toString()
        if(email.isEmpty())
        {
            username.error="Please Enter driver email"
            username.requestFocus()
            return
        }
        var pass=password.text.toString()
        if(pass.isEmpty())
        {
            password.error="Enter password"
            password.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
            username.error = "Please Enter Email"
            username.requestFocus()
            return
        }
        else
        {
            auth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "Successfully registered.",
                            Toast.LENGTH_SHORT).show()
                        gayab.visibility=View.VISIBLE
                        btncreatedriveradmin.visibility=View.INVISIBLE
                        gayab.setOnClickListener {
                            sendmail(username.text.toString(),password.text.toString())
                            finish()
                        }




                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }

                    // ...
                }
        }
    }

    private fun sendmail(username: String, password: String) {
        val intent=Intent(Intent.ACTION_SEND)


        intent.type= "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(username))
        intent.putExtra(Intent.EXTRA_SUBJECT,"welcome driver to waste management!")
        intent.putExtra(Intent.EXTRA_TEXT,"welcome driver to our team your mail id is $username and your password is $password for login into the driver section of the app. You Have to work on BIN ID- 125  and your driver key is #CLEAN")


        try {
            val ch=Intent.createChooser(intent,"Choose medium")
            startActivity(ch)
        }
        catch (e:Exception)
        {


            Toast.makeText(this, e.message,Toast.LENGTH_SHORT).show()
        }





    }


}


