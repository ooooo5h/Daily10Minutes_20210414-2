package com.neppplus.daily10minutes_20210414

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
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

        giveUpBtn.setOnClickListener {

            ServerUtil.deleteRequestGiveUpProject(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                }

            })

        }



        applyBtn.setOnClickListener {

            ServerUtil.postRequestApplyProject(mContext, mProject.id, object : ServerUtil.JsonResponseHandler {

                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    if (code == 200) {

                        val dataObj = jsonObj.getJSONObject("data")
                        val projectObj = dataObj.getJSONObject("project")

                        mProject = Project.getProjectFromJson(projectObj)

                        runOnUiThread {
                            refreshDataToUi()
                        }

                    }
                    else{
                        runOnUiThread {
                            Toast.makeText(mContext, "참여 신청에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            })

        }

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("projectInfo") as Project

        refreshDataToUi()

    }

    fun refreshDataToUi() {
        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)

        titleTxt.text = mProject.title
        descriptionTxt.text = mProject.description

        userCountTxt.text = "${mProject.onGoingUserCount}명"

        proofMethodTxt.text = mProject.proofMethod

        tagListLayout.removeAllViews()

        for (tag in mProject.tags) {

            val tagTextView = TextView(mContext)
            tagTextView.text = "#${tag}"
            tagTextView.setTextColor(Color.BLUE)

            tagListLayout.addView(tagTextView)
        }

    }


}