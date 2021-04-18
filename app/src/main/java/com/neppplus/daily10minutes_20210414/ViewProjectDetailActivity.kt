package com.neppplus.daily10minutes_20210414

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.neppplus.daily10minutes_20210414.datas.Project
import com.neppplus.daily10minutes_20210414.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kotlinx.android.synthetic.main.project_list_item.*
import org.json.JSONObject

class ViewProjectDetailActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        applyBtn.setOnClickListener {

            ServerUtil.postRequestApplyProject(mContext, mProject.id, object : ServerUtil.JsonResponseHandler {

                override fun onResponse(jsonObj: JSONObject) {


                }


            })




        }

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("projectInfo") as Project

        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)

        titleTxt.text = mProject.title
        descriptionTxt.text = mProject.description

        userCountTxt.text = "${mProject.onGoingUserCount}명"

        proofMethodTxt.text = mProject.proofMethod

        for (tag in mProject.tags) {

            val tagTextView = TextView(mContext)
            tagTextView.text = "#${tag}"
            tagTextView.setTextColor(Color.BLUE)

            tagListLayout.addView(tagTextView)

        }


    }


}