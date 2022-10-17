package com.example.wastemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_admin_home_page.*

class adminHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home_page)

        CreateBin.setOnClickListener {
            val bin = Intent(this,CreatebinsAdmin::class.java)
            startActivity(bin)
        }
        CreateDriver.setOnClickListener {
            startActivity(Intent(this,createDriveradmin::class.java))
        }
        UpdateBin.setOnClickListener {
            startActivity(Intent(this,updatebinsadmin::class.java))
        }
        publiccomplaints.setOnClickListener {
            startActivity(Intent(this,public_complaintsadmin::class.java))
        }

        ViewReports.setOnClickListener {
            startActivity(Intent(this,viewReportsadmin::class.java))
        }
    }
}