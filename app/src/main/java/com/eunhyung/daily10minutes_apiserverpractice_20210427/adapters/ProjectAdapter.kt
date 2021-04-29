package com.eunhyung.daily10minutes_apiserverpractice_20210427.adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.eunhyung.daily10minutes_apiserverpractice_20210427.R
import com.eunhyung.daily10minutes_apiserverpractice_20210427.datas.Project

class ProjectAdapter (
    val mContext : Context,
    resId : Int,
    val mList : List<Project>) : ArrayAdapter<Project>(mContext, resId, mList) {

    val inflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inflater.inflate(R.layout.project_list_item, null)
        }
        val row = tempRow!!

        val data = mList[position]

        val backGroundImg = row.findViewById<ImageView>(R.id.backGroundImg)
        val projectTitleTxt = row.findViewById<TextView>(R.id.projectTitleTxt)

        Glide.with(mContext).load(data.imgUrl).into(backGroundImg)
        projectTitleTxt.text = data.title

        return row
    }
}