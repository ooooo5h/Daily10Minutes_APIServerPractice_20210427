package com.eunhyung.daily10minutes_apiserverpractice_20210427.datas

import org.json.JSONObject

class Proof {

    var id = 0
    var content = ""

    val imageUrls = ArrayList<String>()

    companion object {

        fun getProofFromJson ( jsonObj : JSONObject) : Proof {

            val proof = Proof()

            proof.id = jsonObj.getInt("id")
            proof.content = jsonObj.getString("content")

            val imagesArr = jsonObj.getJSONArray("images")

            for (i in 0 until imagesArr.length()) {

                proof.imageUrls.add( imagesArr.getJSONObject(i).getString("img_url"))

            }

            return proof

        }
    }

}