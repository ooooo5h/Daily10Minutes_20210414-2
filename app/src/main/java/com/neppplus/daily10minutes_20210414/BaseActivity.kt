package com.neppplus.daily10minutes_20210414

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()
}