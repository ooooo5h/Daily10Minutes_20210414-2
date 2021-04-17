package com.neppplus.daily10minutes_20210414

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.daily10minutes_20210414.datas.Project
import com.neppplus.daily10minutes_20210414.utils.ServerUtil

class MainActivity : BaseActivity() {

    val mProject = ArrayList<Project>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {
        getProjectListFromServer()



    }

    fun getProjectListFromServer() {

        ServerUtil.


    }


}