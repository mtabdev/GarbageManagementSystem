package com.example.wastemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class public_complaintsadmin : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var List2:MutableList<usercomplaintModalcls>
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_complaintsadmin)

        ref=FirebaseDatabase.getInstance().getReference("User Complaints")
        List2= mutableListOf()
        listView=findViewById(R.id.listview2)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {
                    List2.clear()
                    for (h in snapshot.children) {
                        val comp = h.getValue(usercomplaintModalcls::class.java)

                            List2.add(comp!!)
                        }
                    }
                    val adapter = public_complaints_adaptercls(this@public_complaintsadmin,R.layout.public_complaints_inflator,List2)
                    listView.adapter=adapter

                }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}