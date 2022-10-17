package com.example.wastemanagement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class viewdetailsadapterclass(val mctx: Context, val layoutresId:Int, val binsList:List<createbinmodalcls>)
    : ArrayAdapter<createbinmodalcls>(mctx,layoutresId,binsList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutinflator: LayoutInflater = LayoutInflater.from(mctx)
        val view: View = layoutinflator.inflate(layoutresId, null)


        val tvid = view.findViewById<TextView>(R.id.tv_binid)
        val tvarea = view.findViewById<TextView>(R.id.tv_areaname)
        val tvloc = view.findViewById<TextView>(R.id.tv_locname)
        val tvland = view.findViewById<TextView>(R.id.tv_landmark)
        val tvcity = view.findViewById<TextView>(R.id.tv_city)
        val tvstatus = view.findViewById<TextView>(R.id.tv_status)


        val details = binsList[position]
        tvid.text = details.id
        tvarea.text = details.area
        tvloc.text = details.locality
        tvland.text = details.landmark
        tvcity.text = details.city
        tvstatus.text=details.status

        return view


    }
}