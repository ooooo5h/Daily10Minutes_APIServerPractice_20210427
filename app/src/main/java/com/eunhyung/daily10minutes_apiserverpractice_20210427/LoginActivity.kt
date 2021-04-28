package com.eunhyung.daily10minutes_apiserverpractice_20210427

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.eunhyung.daily10minutes_apiserverpractice_20210427.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()

    }
    override fun setupEvents() {

        loginBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()
            val inputPassword = passwordEdt.text.toString()

            ServerUtil.postRequestLogin(inputEmail, inputPassword, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                    val codeNum = jsonObj.getInt("code")
                    if (codeNum == 200) {
                        
                    }
                    else {

                        val message = jsonObj.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                        
                    }

                }


            })

        }

    }

    override fun setValues() {

    }


}