package com.eunhyung.daily10minutes_apiserverpractice_20210427

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eunhyung.daily10minutes_apiserverpractice_20210427.datas.Project
import com.eunhyung.daily10minutes_apiserverpractice_20210427.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_post_proof.*
import org.json.JSONObject

class PostProofActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_proof)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        postBtn.setOnClickListener {

            val inputContent = proofContentEdt.text.toString()

            ServerUtil.postRequestTodayProof(mContext, mProject.id, inputContent, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")
                    if (code == 200) {
                        finish()

                        }
                    else {

                        val message = jsonObj.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()   // 복귀하면 자동 새로고침해서 작성한 글 바로 나오게는 어떻게 작업..??
                        }

                    }

                }


            })



        }

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project





    }


}