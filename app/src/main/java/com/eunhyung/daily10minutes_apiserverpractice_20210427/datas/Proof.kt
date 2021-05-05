package com.eunhyung.daily10minutes_apiserverpractice_20210427.datas

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Proof {

    var id = 0
    var content = ""

    val imageUrls = ArrayList<String>()

    lateinit var writer : User

//    인증글 작성 일시 저장 변수 => 일시 저장은 캘린더. 캘린더는 내용물만 덮어쓰는 방식
    val proofDateTime = Calendar.getInstance()

    companion object {

        fun getProofFromJson ( jsonObj : JSONObject) : Proof {

            val proof = Proof()

            proof.id = jsonObj.getInt("id")
            proof.content = jsonObj.getString("content")

            val imagesArr = jsonObj.getJSONArray("images")

            for (i in 0 until imagesArr.length()) {

                proof.imageUrls.add( imagesArr.getJSONObject(i).getString("img_url"))

            }

            proof.writer = User.getUserFromJson( jsonObj.getJSONObject("user"))

//            이 글이 작성된 일시도 파싱해야함.(서버가 주는 데이터는 String 이라서 Calendar 로 저장해야함!)
//            1. String 을 파싱해서
            val proofTimeStr = jsonObj.getString("proof_time")

//            서버가 내려주는 양식 그대로 분석
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

//            2. 만들어진 일/시 를 Calendar 양식의 일/시로 변환
            proof.proofDateTime.time = sdf.parse(proofTimeStr)

            return proof

        }
    }

}