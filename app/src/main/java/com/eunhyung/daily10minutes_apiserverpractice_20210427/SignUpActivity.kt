package com.eunhyung.daily10minutes_apiserverpractice_20210427

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.eunhyung.daily10minutes_apiserverpractice_20210427.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import kotlin.math.log

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        emailEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                emailCheckResultTxt.text = "이메일 중복 확인을 해주세요."

            }


        })

        emailCheckBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()

            ServerUtil.getRequestEmailCheck(inputEmail, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {
                    
                    val code = jsonObj.getInt("code")

                    runOnUiThread {
                        if (code == 200) {
                            emailCheckResultTxt.text = "사용해도 좋은 이메일입니다."
                        }
                        else {
                            emailCheckResultTxt.text = "중복된 이메일이라 사용할 수 없습니다."
                        }
                    }


                }


            })
        }

        signUpBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()
            val inputPassword = passwordEdt.text.toString()
            val inputNickname = nicknameEdt.text.toString()

            ServerUtil.putRequestSignUp(inputEmail, inputPassword, inputNickname, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {
                    
                    val code = jsonObj.getInt("code")
                    if (code == 200) {

                        runOnUiThread {
                            Toast.makeText(mContext, "환영합니다", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        
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