package com.neppplus.daily10minutes_20210414.datas

import org.json.JSONObject
import java.io.Serializable

class Project(
    var id : Int,
    var title : String,
    var imageUrl : String,
    var description : String) : Serializable{

    constructor() : this(0,"", "", "")

    companion object {

        fun getProjectFromJson(jsonObj : JSONObject) : Project {
            val project = Project()

            project.id = jsonObj.getInt("id")
            project.title = jsonObj.getString("title")
            project.imageUrl = jsonObj.getString("img_url")
            project.description = jsonObj.getString("description")

            return project
        }
    }
}