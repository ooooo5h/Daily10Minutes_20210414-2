package com.neppplus.daily10minutes_20210414

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.neppplus.daily10minutes_20210414.utils.ContextUtil
import com.neppplus.daily10minutes_20210414.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        autoLoginCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->

            ContextUtil.setAutoLogin(mContext, isChecked)


        }

        signUpBtn.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        loginBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()
            val inputPassword = passwordEdt.text.toString()

            ServerUtil.postRequestLogin(inputEmail, inputPassword, object :ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                    val codeNum = jsonObj.getInt("code")
                    if(codeNum == 200) {

                        val dataObj = jsonObj.getJSONObject("data")
                        val userObj = dataObj.getJSONObject("user")
                        val nickname = userObj.getString("nick_name")

                        val token = dataObj.getString("token")

                        ContextUtil.setLoginToken(mContext, token)


                        runOnUiThread {
                            Toast.makeText(mContext, "${nickname}님 환영합니다", Toast.LENGTH_SHORT).show()

                            val myIntent = Intent(mContext, MainActivity::class.java)
                            startActivity(myIntent)

                            finish()
                        }

                    }
                    else{

                        val message = jsonObj.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }


            })


        }

    }

    override fun setValues() {

        autoLoginCheckBox.isChecked = ContextUtil.getAutoLogin(mContext)



    }


}