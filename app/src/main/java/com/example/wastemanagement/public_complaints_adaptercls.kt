package com.example.wastemanagement

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class public_complaints_adaptercls(val mctx: Context, val layoutresId2:Int, val complaintList:List<usercomplaintModalcls>)
    : ArrayAdapter<usercomplaintModalcls>(mctx,layoutresId2,complaintList)
{

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutinflator: LayoutInflater = LayoutInflater.from(mctx)
        val view:View=layoutinflator.inflate(layoutresId2,null)

        val comkeydisplay = view.findViewById<TextView>(R.id.textviewname)
        val updatecomplaint=view.findViewById<TextView>(R.id.textviewupdatecomp)
        val listcomp=complaintList[position]

        comkeydisplay.text=listcomp.key

        updatecomplaint.setOnClickListener {
            updatecomplaint(listcomp)
        }
        return view
    }

    private fun updatecomplaint(listcomp: usercomplaintModalcls) {


            val alert =AlertDialog.Builder(mctx)
            alert.setTitle("See Complaint")

            val inflator = LayoutInflater.from(mctx)
            val view = inflator.inflate(R.layout.public_complaints_layout,null)


            val name=view.findViewById<EditText>(R.id.usercomplaintsName)
            val areaname=view.findViewById<EditText>(R.id.usercomplaintsareaname)
            val locname=view.findViewById<EditText>(R.id.usercomplaintsLocalityname)
            val landmark=view.findViewById<EditText>(R.id.usercomplaintsLandmark)
            val city=view.findViewById<EditText>(R.id.usercomplaintsCityname)

        val complaints=view.findViewById<EditText>(R.id.usercomplaintsDetails)
            val btnupd=view.findViewById<Button>(R.id.btnUpdatesercomplaints)
            val del=view.findViewById<Button>(R.id.btndeletesercomplaints)
            name.setText(listcomp.name)
            areaname.setText(listcomp.Areaname)
            locname.setText(listcomp.locality)
            landmark.setText(listcomp.landmark)
            city.setText(listcomp.city)
            complaints.setText(listcomp.complaint)
            alert.setView(view)

            btnupd.setOnClickListener {
                val dbref= FirebaseDatabase.getInstance().getReference("User Complaints")
                val k=listcomp.key
                val a=name.text.toString()
                val b=areaname.text.toString()
                val c=locname.text.toString()
                val d=landmark.text.toString()
                val e=city.text.toString()
                val f=complaints.text.toString()


                val upd = usercomplaintModalcls(k,a,b,c,d,e,f)
                dbref.child(a).setValue(upd)
                Toast.makeText(mctx,"Updated Successfully!", Toast.LENGTH_SHORT).show()


            }

            del.setOnClickListener {

                val d = FirebaseDatabase.getInstance().getReference("User Complaints")
                val id=listcomp.key.toString()
                d.child(id).removeValue()
                Toast.makeText(mctx,"Deleted!", Toast.LENGTH_SHORT).show()

            }

            val alertdialog=alert.create()
            alertdialog.show()
        }

    }


