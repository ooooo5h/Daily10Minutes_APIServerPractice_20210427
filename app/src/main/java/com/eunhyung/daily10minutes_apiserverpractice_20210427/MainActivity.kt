package com.eunhyung.daily10minutes_apiserverpractice_20210427

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.eunhyung.daily10minutes_apiserverpractice_20210427.adapters.ProjectAdapter
import com.eunhyung.daily10minutes_apiserverpractice_20210427.datas.Project
import com.eunhyung.daily10minutes_apiserverpractice_20210427.utils.ContextUtil
import com.eunhyung.daily10minutes_apiserverpractice_20210427.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mProject = ArrayList<Project>()

    lateinit var mProjectAdapter : ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        projectListView.setOnItemClickListener { parent, view, position, id ->

            val clickedProject = mProject[position]

            val myIntent = Intent(mContext, ViewProjectDetailActivity::class.java)
            myIntent.putExtra("projectInfo", clickedProject)
            startActivity(myIntent)



        }

        logoutBtn.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃")
            alert.setMessage("정말 로그아웃하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

                ContextUtil.setLoginToken(mContext, "")

                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)

                finish()


            })
            alert.setNegativeButton("취소", null)
            alert.show()


        }

    }

    override fun setValues() {

        getProjectListFromServer()

        mProjectAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProject)
        projectListView.adapter = mProjectAdapter

        backImg.visibility = View.GONE

    }

    fun getProjectListFromServer() {

        ServerUtil.getRequestProjectList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val projectsArr = dataObj.getJSONArray("projects")

                for (i in 0 until projectsArr.length()) {

                    val projectObj = projectsArr.getJSONObject(i)

                    val project = Project.getProjectFromJson(projectObj)

                    mProject.add(project)

                }

                runOnUiThread {
                    mProjectAdapter.notifyDataSetChanged()
                }


            }


        })

    }


}