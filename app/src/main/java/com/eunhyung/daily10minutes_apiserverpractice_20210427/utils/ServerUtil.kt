package com.eunhyung.daily10minutes_apiserverpractice_20210427.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {


    interface JsonResponseHandler {
        fun onResponse(jsonObj : JSONObject)

    }

    companion object {

        val HOST_URL = "http://15.164.153.174"

        fun postRequestLogin(email : String, pw : String, handler: JsonResponseHandler?) {

            val urlString = "${HOST_URL}/user"

            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build()

            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버응답", jsonObj.toString())

                    handler?.onResponse(jsonObj)

                }


            })


        }


    }


}