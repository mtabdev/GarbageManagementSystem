package com.example.wastemanagement

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.firebase.database.*

class driverDash : AppCompatActivity() {
    lateinit var src:EditText
    lateinit var des:EditText
    lateinit var btn:Button


    lateinit var ref: DatabaseReference
    lateinit var List:MutableList<createbinmodalcls>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_dash)
        src=findViewById(R.id.etsource)
        des=findViewById(R.id.etdest)
        btn=findViewById(R.id.btnshowmap)

        listView=findViewById(R.id.listview3)
        List= mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Create Bins")


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot!!.exists())
                {
                    List.clear()
                    for(h in snapshot.children)
                    {
                        val bin=h.getValue(createbinmodalcls::class.java)
                        List.add(bin!!)
                    }
                    val adapter = driverViewdetailsadapter(this@driverDash,R.layout.driverupdates,List)
                    listView.adapter=adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }}










































/*
        btn.setOnClickListener {
            val s=src.text.toString().trim()
            val d=des.text.toString().trim()
            showmap(s,d)
        }

    }
    fun showmap(s: String, d: String)
    {
        val uri=Uri.parse("https://www.google.co.in/maps/dir/" + s + "/" + d)
        val intent=Intent(Intent.ACTION_VIEW,uri)

        intent.setPackage("com.google.android.apps.maps")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(intent)

    }
}
*?
 */