package com.eunhyung.daily10minutes_apiserverpractice_20210427.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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



    }


}