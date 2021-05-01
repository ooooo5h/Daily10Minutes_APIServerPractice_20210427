package com.eunhyung.daily10minutes_apiserverpractice_20210427.datas

import org.json.JSONObject
import java.io.Serializable

class Project (
    var id : Int,
    var title : String,
    var imgUrl : String,
    var description : String): Serializable {

//    보조생성자 추가해서 Project()만으로도 만들 수 있게 하자

    constructor() : this(0, "", "", "")

    companion object {

        fun getProjectFromJson(jsonObj : JSONObject) : Project {

            val project = Project()

            project.id = jsonObj.getInt("id")
            project.title = jsonObj.getString("title")
            project.imgUrl = jsonObj.getString("img_url")
            project.description = jsonObj.getString("description")

            return project
        }
    }

}