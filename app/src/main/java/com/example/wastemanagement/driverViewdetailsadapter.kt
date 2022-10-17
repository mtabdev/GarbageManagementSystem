package com.example.wastemanagement

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.FirebaseDatabase

class driverViewdetailsadapter (val mctx: Context, val layoutresId:Int, val binsList:List<createbinmodalcls>)
    : ArrayAdapter<createbinmodalcls>(mctx,layoutresId,binsList)
{


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutinflator: LayoutInflater = LayoutInflater.from(mctx)
        val view: View =layoutinflator.inflate(layoutresId,null)

        val textviewdisplay = view.findViewById<TextView>(R.id.textViewdisplay3)
        val textviewupdate=view.findViewById<TextView>(R.id.textviewupdatebin3)
        val listbin=binsList[position]

        textviewdisplay.text=listbin.id
        textviewupdate.setOnClickListener {
            updateBin(listbin)
        }
        return view
    }

    private fun updateBin(listbin: createbinmodalcls) {
        val alert = AlertDialog.Builder(mctx)
        alert.setTitle("Update data")

        val inflator = LayoutInflater.from(mctx)
        val view = inflator.inflate(R.layout.driverbindialog,null)

        val bino=view.findViewById<EditText>(R.id.createbinid)
        val areaname=view.findViewById<EditText>(R.id.areaNameAdmin)
        val locname=view.findViewById<EditText>(R.id.localityNameAdmin)
        val landmark=view.findViewById<EditText>(R.id.landmarkBinAdmin)
        val city=view.findViewById<EditText>(R.id.cityBinAdmin)
        val spn=view.findViewById<Spinner>(R.id.spnupd)
        val spncycle=view.findViewById<Spinner>(R.id.spinnercycleperoid)
        val btnupd=view.findViewById<Button>(R.id.btnSaveBinAdmin)
        val del=view.findViewById<Button>(R.id.btndeletebin)

        val map=view.findViewById<Button>(R.id.btnshowmapdriver)

        val src=view.findViewById<EditText>(R.id.driversourceaddress)
        //spinner area

        val dst=view.findViewById<EditText>(R.id.et_driverstatus)













        //till here
        bino.setText(listbin.id)
        areaname.setText(listbin.area)
        locname.setText(listbin.locality)
        landmark.setText(listbin.landmark)
        city.setText(listbin.city)
        dst.setText(listbin.status)
        val options= arrayOf<String>("low","medium","high")
        spn.adapter= ArrayAdapter<String>(mctx,android.R.layout.simple_list_item_1,options)
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
            val dbref= FirebaseDatabase.getInstance().getReference("Create Bins")
            val a=bino.text.toString()
            val b=areaname.text.toString()
            val c=locname.text.toString()
            val d=landmark.text.toString()
            val e=city.text.toString()
            val f=spn.selectedItem.toString()
            val g=spncycle.selectedItem.toString()
            val h=dst.text.toString()
            //spinner

            //spinner ends
            val upd = createbinmodalcls(a,b,c,d,e,f,g,h)
            dbref.child(a).setValue(upd)
            Toast.makeText(mctx,"Updated Successfully!", Toast.LENGTH_SHORT).show()


        }

        del.setOnClickListener {

            val d = FirebaseDatabase.getInstance().getReference("Create Bins")
            val id=bino.text.toString()
            d.child(id).removeValue()
            Toast.makeText(mctx,"Deleted!", Toast.LENGTH_SHORT).show()

        }

        map.setOnClickListener {

            val sourcemap=src.text.toString()

            val b=areaname.text.toString()
            val c=locname.text.toString()
            val d=landmark.text.toString()
            val e=city.text.toString()
            showmap(src,b,c,d,e)
        }

        val alertdialog=alert.create()
        alertdialog.show()
    }

    private fun showmap(src: EditText, b: String, c: String, d: String, e: String) {
        val dest=b+" "+c+" "+d+" "+e
        val sour=src.text.toString().trim()


        val uri= Uri.parse("https://www.google.co.in/maps/dir/" + sour + "/" + dest)
        val intent= Intent(Intent.ACTION_VIEW,uri)

        intent.setPackage("com.google.android.apps.maps")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(context,intent,null)

    }


}