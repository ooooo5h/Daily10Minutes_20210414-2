package com.neppplus.daily10minutes_20210414.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

    interface JsonResponseHandler {
        fun onResponse(jsonObj: JSONObject)

    }

    companion object {

        val HOST_URL = "http://15.164.153.174"

        fun postRequestLogin(email: String, pw: String, handler: JsonResponseHandler?) {

            val urlString = "${HOST_URL}/user"

            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build()

            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val jsonObj = JSONObject(bodyString)

                    handler?.onResponse(jsonObj)

                }


            })


        }

        fun putRequestSignUp(email: String, pw: String,nickname: String, handler: JsonResponseHandler?        ) {

            val urlString = "${HOST_URL}/user"

            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nickname)
                .build()

            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val jsonObj = JSONObject(bodyString)

                    handler?.onResponse(jsonObj)
                }


            })
        }

        fun getRequestEmailCheck(email : String, handler: JsonResponseHandler?) {

            val urlBuilder = "${HOST_URL}/email_check".toHttpUrlOrNull()!!.newBuilder()

            urlBuilder.addEncodedQueryParameter("email", email)

            val urlString = urlBuilder.build().toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
                .build()

            val client = OkHttpClient()
            
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()
                    val jsonObj= JSONObject(bodyString)

                    Log.d("서버응답본문", jsonObj.toString())

                    handler?.onResponse(jsonObj)

                }


            })


        }

        fun getRequestProjectList(context : Context, handler: JsonResponseHandler?) {

            val urlBuilder = "${HOST_URL}/project".toHttpUrlOrNull()!!.newBuilder()

//            urlBuilder.addEncodedQueryParameter("email", email)

            val urlString = urlBuilder.build().toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginToken(context))
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()
                    val jsonObj= JSONObject(bodyString)

                    Log.d("서버응답본문", jsonObj.toString())

                    handler?.onResponse(jsonObj)

                }


            })


        }

        fun postRequestApplyProject(context: Context, projectId : Int, handler: JsonResponseHandler?) {

            val urlString = "${HOST_URL}/project"

            val formData = FormBody.Builder()
                .add("project_id", projectId.toString())
                .build()

            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .header("X-Http-Token", ContextUtil.getLoginToken(context))
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

                    val jsonObj = JSONObject(bodyString)

                    handler?.onResponse(jsonObj)

                }


            })


        }









    }


}


