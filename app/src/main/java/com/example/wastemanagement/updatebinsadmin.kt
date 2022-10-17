package com.example.wastemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_updatebinsadmin.*
import java.lang.StringBuilder


class updatebinsadmin : AppCompatActivity() {

       lateinit var ref:DatabaseReference
    lateinit var List:MutableList<createbinmodalcls>
    lateinit var listView:ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updatebinsadmin)
            listView=findViewById(R.id.listView)
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
            val adapter = updateBinsadapter(this@updatebinsadmin,R.layout.binslayoutupdate,List)
            listView.adapter=adapter
            }
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }
})
    }
}