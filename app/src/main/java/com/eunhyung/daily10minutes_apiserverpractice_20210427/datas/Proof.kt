package com.eunhyung.daily10minutes_apiserverpractice_20210427.datas

import org.json.JSONObject

class Proof {

    var id = 0
    var content = ""

    companion object {

        fun getProofFromJson ( jsonObj : JSONObject) : Proof {

            val proof = Proof()

            proof.id = jsonObj.getInt("id")
            proof.content = jsonObj.getString("content")

            return proof

        }
    }

}