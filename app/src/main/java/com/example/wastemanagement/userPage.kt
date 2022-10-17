package com.example.wastemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_user_page.*

class userPage : AppCompatActivity() {
    lateinit var username:EditText
    lateinit var aname:EditText
    lateinit var locname:EditText
    lateinit var landname:EditText
    lateinit var city:EditText
    lateinit var comments:EditText
    lateinit var data:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)

        username=findViewById(R.id.usercomplaintsName)
        aname=findViewById(R.id.usercomplaintsareaname)
        locname=findViewById(R.id.usercomplaintsLocalityname)
        landname=findViewById(R.id.usercomplaintsLandmark)
        city=findViewById(R.id.usercomplaintsCityname)
        comments=findViewById(R.id.usercomplaintsDetails)


        btnsercomplaints.setOnClickListener {
            saveData()
        }
    }

    private fun saveData()
    {
        val uname=username.text.toString()
        if(uname.isEmpty())
        {
            username.error="Please Enter Your Name"
            username.requestFocus()
            return
        }
        val anm=aname.text.toString()
        if(anm.isEmpty())
        {
            aname.error="Enter Your Area Name"
            aname.requestFocus()
            return
        }

        val lnam=locname.text.toString()
        if(lnam.isEmpty())
        {
            locname.error="Enter Your Locality Name"
            locname.requestFocus()
            return
        }
        val lm=landname.text.toString()
        if(lm.isEmpty())
        {
            landname.error="Enter landmark"
            landname.requestFocus()
            return
        }
        val c=city.text.toString()
        if(c.isEmpty())
        {
            city.error="Enter city name"
            city.requestFocus()
            return
        }

        val comp=comments.text.toString()
        if(comp.isEmpty())
        {
            comments.error="Please specify the problem"
            comments.requestFocus()
        }

        else {

            val usercomplaintdbmsreff =
                FirebaseDatabase.getInstance().getReference("User Complaints")
            val keyusercomplaint = usercomplaintdbmsreff.push().key

            val passusercomplainttomodalclass =
                keyusercomplaint?.let { usercomplaintModalcls(it, uname, anm, lnam, lm, c, comp) }




            if (keyusercomplaint != null) {
                usercomplaintdbmsreff.child(keyusercomplaint)
                    .setValue(passusercomplainttomodalclass).addOnCompleteListener {
                    Toast.makeText(this, "Complaint sent Successfully!", Toast.LENGTH_SHORT).show()
                }
            } else {

                Toast.makeText(this, "Data Transfer Failed!", Toast.LENGTH_SHORT).show()
            }
        }

        }
    }
