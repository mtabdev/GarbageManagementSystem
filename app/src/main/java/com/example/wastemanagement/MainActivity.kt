package com.example.wastemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn = findViewById<Button>(R.id.button1)
        var btn2 = findViewById<Button>(R.id.button2)
        var btn3 = findViewById<Button>(R.id.button3)
        var btn4 = findViewById<Button>(R.id.button4)

        btn.setOnClickListener {
          val intentAdmin = Intent(this,admin::class.java)
            startActivity(intentAdmin)
        }
        btn3.setOnClickListener {
            val intentDriver = Intent(this,User::class.java)
            startActivity(intentDriver)
        }
        btn2.setOnClickListener {
            val intentUser = Intent(this,Driver::class.java)
            startActivity(intentUser)
        }
        btn4.setOnClickListener {
            val intentRegistration = Intent(this,Registration::class.java)
            startActivity(intentRegistration)
        }

    }
}