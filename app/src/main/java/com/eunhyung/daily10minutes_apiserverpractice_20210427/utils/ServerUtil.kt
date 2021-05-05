package com.eunhyung.daily10minutes_apiserverpractice_20210427.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {


    interface JsonResponseHandler {
        fun onResponse(jsonObj : JSONObject)

    }

    companion object {

        val HOST_URL = "http://15.164.153.174"

//        서버에 로그인 요청하는 기능

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

//        회원가입 기능

        fun putRequestSignUp(email : String, pw : String, nickname : String, handler: JsonResponseHandler?) {

            val urlString = "${HOST_URL}/user"

            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nickname)
                .build()

            val request = Request.Builder()
                .url(urlString)
                .put(formData)
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

//        이메일 중복 체크 기능

        fun getRequestEmailCheck(email : String, handler: JsonResponseHandler?) {

            val urlBuilder = "${HOST_URL}/email_check".toHttpUrlOrNull()!!.newBuilder()

            urlBuilder.addEncodedQueryParameter("email", email)

            val urlString = urlBuilder.build().toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
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

//        프로젝트 목록 받아오기

        fun getRequestProjectList(context : Context, handler: JsonResponseHandler?) {

            val urlBuilder = "${HOST_URL}/project".toHttpUrlOrNull()!!.newBuilder()

//            urlBuilder.addEncodedQueryParameter("X-Http-Token", ContextUtil.getLoginToken(context))

            val urlString = urlBuilder.build().toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginToken(context))
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

//        프로젝트 참여 신청

        fun postRequestApplyProject(context: Context, projectId : Int, handler: JsonResponseHandler?) {

            val urlString = "${HOST_URL}/project"

            val formData = FormBody.Builder()
                .add("project_id", projectId.toString())
                .build()

            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .header("X-Http-Token", ContextUtil.getLoginToken(context))
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

//        프로젝트 포기하기

        fun deleteRequestGiveUpProject(context : Context, projectId : Int, handler: JsonResponseHandler?) {

            val urlBuilder = "${HOST_URL}/project".toHttpUrlOrNull()!!.newBuilder()

            urlBuilder.addEncodedQueryParameter("project_id", projectId.toString())

            val urlString = urlBuilder.build().toString()

            val request = Request.Builder()
                .url(urlString)
                .delete()
                .header("X-Http-Token", ContextUtil.getLoginToken(context))
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

//        특정 프로젝트 상세보기

        fun getRequestProjectDetail(context : Context,projectId : Int, handler: JsonResponseHandler?) {

            val urlBuilder = "${HOST_URL}/project".toHttpUrlOrNull()!!.newBuilder()

            urlBuilder.addEncodedPathSegment(projectId.toString())

//            urlBuilder.addEncodedQueryParameter("X-Http-Token", ContextUtil.getLoginToken(context))

            val urlString = urlBuilder.build().toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginToken(context))
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

//       특정 프로젝트의 인증글을 날짜별로 받아오기

        fun getRequestProofListByDate(context : Context,projectId : Int, date : String, handler: JsonResponseHandler?) {

            val urlBuilder = "${HOST_URL}/project".toHttpUrlOrNull()!!.newBuilder()

            urlBuilder.addEncodedPathSegment(projectId.toString())

            urlBuilder.addEncodedQueryParameter("proof_date", date)

            val urlString = urlBuilder.build().toString()

            Log.d("가공된URL", urlString)

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginToken(context))
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

//        오늘의 인증 등록

        fun postRequestTodayProof(context: Context, projectId : Int, content : String, handler: JsonResponseHandler?) {

            val urlString = "${HOST_URL}/project_proof"

            val formData = FormBody.Builder()
                .add("project_id", projectId.toString())
                .add("content", content)
//                .add("X-Http-Token", ContextUtil.getLoginToken(context))  : X-Http-Token이 formData로 되어있어도 그거랑 무관하게 헤더에 담는거니까 헤더에 담는건지?
                .build()

            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .header("X-Http-Token", ContextUtil.getLoginToken(context))  // header는 내용에 없는데도 추가하는게 맞는지..?
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