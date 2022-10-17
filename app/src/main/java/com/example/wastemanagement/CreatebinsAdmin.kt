package com.example.wastemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_createbins_admin.*

class CreatebinsAdmin : AppCompatActivity() {

    lateinit var binId: EditText
    lateinit var areaname: EditText
    lateinit var locname: EditText
    lateinit var landmark: EditText
    lateinit var cty: EditText
    lateinit var status:EditText



    lateinit var spn:Spinner
    lateinit var spncycle:Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createbins_admin)

        binId = findViewById(R.id.createbinid)
        areaname = findViewById(R.id.areaNameAdmin)
        locname = findViewById(R.id.localityNameAdmin)
        landmark = findViewById(R.id.landmarkBinAdmin)
        cty = findViewById(R.id.cityBinAdmin)


        status=findViewById(R.id.etstatus)


        spn=findViewById(R.id.spinnercreatebins)
        spncycle=findViewById(R.id.spinnercycleperoid)


        val options= arrayOf("High","Low","Medium")
        spn.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
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
        spncycle.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options2)
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



        btnSaveBinAdmin.setOnClickListener {
            savetoDBMS()
        }
    }

    private fun savetoDBMS() {
        val id = binId.text.toString()
        if (id.isEmpty()) {
            binId.error = "please enter bin id"
            binId.requestFocus()
        }


        val aname = areaname.text.toString()
        if (aname.isEmpty()) {
            areaname.error = "Please Enter area name"
            areaname.requestFocus()
            return
        }
        val lname = locname.text.toString()
        if (lname.isEmpty()) {
            locname.error = "Please Enter area name"
            locname.requestFocus()
            return
        }
        val lmark = landmark.text.toString()
        if (lmark.isEmpty()) {
            landmark.error = "Please Enter area name"
            landmark.requestFocus()
            return
        }

        val c = cty.text.toString()
        if (c.isEmpty()) {
            cty.error = "Please Enter area name"
            cty.requestFocus()
            return
        }
        val st=status.text.toString()
        if(st.isEmpty())
        {
            status.error="Please enter complete or incomplete"
            status.requestFocus()
        }



        val binReference = FirebaseDatabase.getInstance().getReference("Create Bins")


        val passtomodalcls = createbinmodalcls(id,aname, lname, lmark, c,spn.selectedItem.toString(),spncycle.selectedItem.toString(),st)



        binReference.child(id).setValue(passtomodalcls).addOnCompleteListener {
            Toast.makeText(this, "Bin Created Successfully!", Toast.LENGTH_SHORT).show()
            finish()


        }
    }



    }
