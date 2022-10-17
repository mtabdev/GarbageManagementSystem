package com.example.wastemanagement

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import android.widget.ArrayAdapter as ArrayAdapter1

class updateBinsadapter(val mctx:Context, val layoutresId:Int,val binsList:List<createbinmodalcls>)
    : ArrayAdapter1<createbinmodalcls>(mctx,layoutresId,binsList)





{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutinflator:LayoutInflater= LayoutInflater.from(mctx)
        val view:View=layoutinflator.inflate(layoutresId,null)

        val textviewdisplay = view.findViewById<TextView>(R.id.textViewdisplay)
        val textviewupdate=view.findViewById<TextView>(R.id.textviewupdatebin)
        val listbin=binsList[position]

        textviewdisplay.text=listbin.id
        textviewupdate.setOnClickListener {
            updateBin(listbin)
        }
        return view
    }

    private fun updateBin(listbin: createbinmodalcls) {
        val alert =AlertDialog.Builder(mctx)
        alert.setTitle("Update data")

        val inflator = LayoutInflater.from(mctx)
        val view = inflator.inflate(R.layout.updatebindialog,null)

        val bino=view.findViewById<EditText>(R.id.createbinid)
        val areaname=view.findViewById<EditText>(R.id.areaNameAdmin)
        val locname=view.findViewById<EditText>(R.id.localityNameAdmin)
        val landmark=view.findViewById<EditText>(R.id.landmarkBinAdmin)
        val city=view.findViewById<EditText>(R.id.cityBinAdmin)
        val spn=view.findViewById<Spinner>(R.id.spnupd)
        val spncycle=view.findViewById<Spinner>(R.id.spinnercycleperoid)
        val btnupd=view.findViewById<Button>(R.id.btnSaveBinAdmin)
        val del=view.findViewById<Button>(R.id.btndeletebin)
        //spinner area

        val sts=view.findViewById<EditText>(R.id.et_updatebinstatus)













        //till here
        bino.setText(listbin.id)
        areaname.setText(listbin.area)
        locname.setText(listbin.locality)
        landmark.setText(listbin.landmark)
        city.setText(listbin.city)
        sts.setText(listbin.status)
        val options= arrayOf<String>("low","medium","high")
     spn.adapter= ArrayAdapter1<String>(mctx,android.R.layout.simple_list_item_1,options)
        spn.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    p0.getItemAtPosition(p2)

                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


        val options2= arrayOf<String>("Daily","Weekly","Monthly")
        spncycle.adapter=
            android.widget.ArrayAdapter<String>(mctx, android.R.layout.simple_list_item_1, options2)
        spncycle.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    p0.getItemAtPosition(p2)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        alert.setView(view)

        btnupd.setOnClickListener {
            val dbref=FirebaseDatabase.getInstance().getReference("Create Bins")
            val a=bino.text.toString()
            val b=areaname.text.toString()
            val c=locname.text.toString()
            val d=landmark.text.toString()
            val e=city.text.toString()
            val f=spn.selectedItem.toString()
            val g=spncycle.selectedItem.toString()
            val h=sts.text.toString()
            //spinner

        //spinner ends
            val upd = createbinmodalcls(a,b,c,d,e,f,g,h)
            dbref.child(a).setValue(upd)
            Toast.makeText(mctx,"Updated Successfully!",Toast.LENGTH_SHORT).show()


        }

        del.setOnClickListener {

            val d =FirebaseDatabase.getInstance().getReference("Create Bins")
            val id=bino.text.toString()
            d.child(id).removeValue()
            Toast.makeText(mctx,"Deleted!",Toast.LENGTH_SHORT).show()

        }

        val alertdialog=alert.create()
        alertdialog.show()
    }


}


