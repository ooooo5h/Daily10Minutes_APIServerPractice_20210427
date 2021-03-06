package com.eunhyung.daily10minutes_apiserverpractice_20210427

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.eunhyung.daily10minutes_apiserverpractice_20210427.datas.Project
import com.eunhyung.daily10minutes_apiserverpractice_20210427.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import org.json.JSONObject

class ViewProjectDetailActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        viewProofBtn.setOnClickListener {

            val myIntent = Intent(mContext, ViewProofByDateActivity::class.java)
            myIntent.putExtra("projectInfo", mProject)
            startActivity(myIntent)
        }

        giveUpBtn.setOnClickListener {

            ServerUtil.deleteRequestGiveUpProject(
                mContext,
                mProject.id,
                object :
                    ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {

                        val code = jsonObj.getInt("code")
                        if (code == 200) {

                            ServerUtil.getRequestProjectDetail(
                                mContext,
                                mProject.id,
                                object :
                                    ServerUtil.JsonResponseHandler {
                                    override fun onResponse(jsonObj: JSONObject) {

                                        val dataObj = jsonObj.getJSONObject("data")
                                        val projectObj = dataObj.getJSONObject("project")

                                        mProject = Project.getProjectFromJson(projectObj)

                                        runOnUiThread {
                                            refreshDataToUi()
                                        }

                                    }


                                })

                        } else {
                            runOnUiThread {
                                Toast.makeText(mContext, "??????????????? ??????????????????.", Toast.LENGTH_SHORT).show()
                            }

                        }

                    }


                })

        }

        applyBtn.setOnClickListener {

            ServerUtil.postRequestApplyProject(
                mContext,
                mProject.id,
                object :
                    ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {

                        val code = jsonObj.getInt("code")
                        if (code == 200) {

                            val dataObj = jsonObj.getJSONObject("data")
                            val projectObj = dataObj.getJSONObject("project")

                            mProject = Project.getProjectFromJson(projectObj)

                            runOnUiThread {
                                refreshDataToUi()
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(mContext, "?????? ????????? ??????????????????.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                })
        }
    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("projectInfo") as Project

        refreshDataToUi()

    }

    fun refreshDataToUi() {

        Glide.with(mContext).load(mProject.imgUrl).into(projectImg)
        titleTxt.text = mProject.title
        descriptionTxt.text = mProject.description

        userCountTxt.text = "${mProject.ongoingUserCount}???"
        proofMethodTxt.text = mProject.proofMethod

        tagListLayout.removeAllViews()

        for (tag in mProject.tags) {

            Log.d("???????????? ??????", tag)

            val tagTextView = TextView(mContext)
            tagTextView.text = "#${tag}"
            tagTextView.setTextColor(Color.MAGENTA)

            tagListLayout.addView(tagTextView)
        }

        if (mProject.myLastStatus == "ONGOING") {

            giveUpBtn.visibility = View.VISIBLE
            applyBtn.visibility = View.GONE
        }
        else {

            giveUpBtn.visibility = View.GONE
            applyBtn.visibility = View.VISIBLE
        }

    }




}