package com.eunhyung.daily10minutes_apiserverpractice_20210427

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eunhyung.daily10minutes_apiserverpractice_20210427.adapters.ProjectAdapter
import com.eunhyung.daily10minutes_apiserverpractice_20210427.datas.Project
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

    }

    override fun setValues() {

        getProjectListFromServer()

        mProjectAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProject)
        projectListView.adapter = mProjectAdapter

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