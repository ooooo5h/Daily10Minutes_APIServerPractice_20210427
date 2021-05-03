package com.eunhyung.daily10minutes_apiserverpractice_20210427.adapters

import android.content.Context
import android.media.Image
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.eunhyung.daily10minutes_apiserverpractice_20210427.R
import com.eunhyung.daily10minutes_apiserverpractice_20210427.datas.Project
import com.eunhyung.daily10minutes_apiserverpractice_20210427.datas.Proof

class ProofAdapter (
    val mContext : Context,
    resId : Int,
    val mList : List<Proof>) : ArrayAdapter<Proof>(mContext, resId, mList) {

    val inflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inflater.inflate(R.layout.proof_list_item, null)
        }
        val row = tempRow!!

        val proofDate = mList[position]

        val writerProfileImg = row.findViewById<ImageView>(R.id.writerProfileImg)
        val writerNicknameTxt = row.findViewById<TextView>(R.id.writerNicknameTxt)
        val proofTimeTxt = row.findViewById<TextView>(R.id.proofTimeTxt)
        val proofContentTxt = row.findViewById<TextView>(R.id.proofContentTxt)
        val proofImg = row.findViewById<ImageView>(R.id.proofImg)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val replyBtn = row.findViewById<Button>(R.id.replyBtn)

        proofContentTxt.text = proofDate.content

        return row
    }
}