package com.eunhyung.daily10minutes_apiserverpractice_20210427

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eunhyung.daily10minutes_apiserverpractice_20210427.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()

    }
    override fun setupEvents() {

        loginBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()
            val inputPassword = passwordEdt.text.toString()

            ServerUtil.postRequestLogin(inputEmail, inputPassword, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                }


            })

        }

    }

    override fun setValues() {

    }


}