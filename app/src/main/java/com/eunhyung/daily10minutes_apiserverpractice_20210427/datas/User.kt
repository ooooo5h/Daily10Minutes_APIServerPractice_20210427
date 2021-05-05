package com.eunhyung.daily10minutes_apiserverpractice_20210427.datas

import org.json.JSONObject

class User {

    var id = 0
    var email = ""
    var nickname = ""

    val profileImageUrl = ArrayList<String>()

    companion object {

        fun getUserFromJson(jsonObj : JSONObject) : User {

            val user = User()

            user.id = jsonObj.getInt("id")
            user.email = jsonObj.getString("email")
            user.nickname = jsonObj.getString("nick_name")

            val profileArr = jsonObj.getJSONArray("profile_images")
            for (i in 0 until profileArr.length()) {

                user.profileImageUrl.add(profileArr.getJSONObject(i).getString("img_url"))

            }



            return user



        }
    }
}