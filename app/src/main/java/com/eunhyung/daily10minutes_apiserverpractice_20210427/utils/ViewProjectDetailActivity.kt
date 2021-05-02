package com.eunhyung.daily10minutes_apiserverpractice_20210427.utils

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import com.eunhyung.daily10minutes_apiserverpractice_20210427.BaseActivity
import com.eunhyung.daily10minutes_apiserverpractice_20210427.R
import com.eunhyung.daily10minutes_apiserverpractice_20210427.datas.Project
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kotlinx.android.synthetic.main.project_list_item.*

class ViewProjectDetailActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("projectInfo") as Project

        Glide.with(mContext).load(mProject.imgUrl).into(projectImg)
        titleTxt.text = mProject.title
        descriptionTxt.text = mProject.description

        userCountTxt.text = "${mProject.ongoingUserCount}명"
        proofMethodTxt.text = mProject.proofMethod

        for (tag in mProject.tags) {

            Log.d("프로젝트 태그", tag)

            val tagTextView = TextView(mContext)
            tagTextView.text = "#${tag}"
            tagTextView.setTextColor(Color.MAGENTA)

            tagListLayout.addView(tagTextView)
        }



    }


}