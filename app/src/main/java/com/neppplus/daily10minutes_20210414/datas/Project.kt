package com.neppplus.daily10minutes_20210414.datas

import org.json.JSONObject
import java.io.Serializable

class Project(
    var id : Int,
    var title : String,
    var imageUrl : String,
    var description : String,
    var onGoingUserCount : Int,
    var proofMethod : String) : Serializable {

    val tags = ArrayList<String>()

    constructor() : this(0,"", "", "", 0, "")

    companion object {

        fun getProjectFromJson(jsonObj : JSONObject) : Project {
            val project = Project()

            project.id = jsonObj.getInt("id")
            project.title = jsonObj.getString("title")
            project.imageUrl = jsonObj.getString("img_url")
            project.description = jsonObj.getString("description")

            project.onGoingUserCount = jsonObj.getInt("ongoing_users_count")

            project.proofMethod = jsonObj.getString("proof_method")

            val tagsArr = jsonObj.getJSONArray("tags")

            for (index in 0 until tagsArr.length()) {

                val tagObj = tagsArr.getJSONObject(index)
                val title = tagObj.getString("title")

                project.tags.add(title)
            }

            return project
        }
    }
}