package com.example.wastemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class viewReportsadmin : AppCompatActivity() {
    lateinit var ref:DatabaseReference
    lateinit var List:MutableList<createbinmodalcls>
    lateinit var listView4: ListView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reportsadmin)

        ref=FirebaseDatabase.getInstance().getReference("Create Bins")
        List= mutableListOf()
        listView4=findViewById(R.id.listviewidviewrepots)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot!!.exists())
                {
                    for(h in snapshot.children)
                    {
                        val bin= h.getValue(createbinmodalcls::class.java)
                        if (bin != null) {
                            if(bin.status=="complete")
                                List.add(bin!!)
                        }
                    }

                    val adapter=viewdetailsadapterclass(this@viewReportsadmin,R.layout.viewreportslayout,List)
                    listView4.adapter=adapter
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}