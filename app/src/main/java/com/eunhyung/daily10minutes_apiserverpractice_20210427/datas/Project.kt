package com.eunhyung.daily10minutes_apiserverpractice_20210427.datas

import org.json.JSONObject
import java.io.Serializable

class Project (
    var id : Int,
    var title : String,
    var imgUrl : String,
    var description : String,
    var ongoingUserCount : Int,
    var proofMethod : String): Serializable {

    val tags = ArrayList<String>()

//    보조생성자 추가해서 Project()만으로도 만들 수 있게 하자

    constructor() : this(0, "", "", "", 0, "")

    companion object {

        fun getProjectFromJson(jsonObj : JSONObject) : Project {

            val project = Project()

            project.id = jsonObj.getInt("id")
            project.title = jsonObj.getString("title")
            project.imgUrl = jsonObj.getString("img_url")
            project.description = jsonObj.getString("description")

            project.ongoingUserCount = jsonObj.getInt("ongoing_users_count")
            project.proofMethod = jsonObj.getString("proof_method")

            val tagsArr = jsonObj.getJSONArray("tags")
            for (i in 0 until tagsArr.length()) {

                val tagObj = tagsArr.getJSONObject(i)

                val tagTitle = tagObj.getString("title")

                project.tags.add(tagTitle)

            }


            return project
        }
    }

}