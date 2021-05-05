package com.eunhyung.daily10minutes_apiserverpractice_20210427

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.my_custom_action_bar.*

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()

    lateinit var backImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.let {

            setCustomActionBar()
        }

    }

    fun setCustomActionBar() {

        val defaultActionBar = supportActionBar!!

        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)

        val myToolBar = defaultActionBar.customView.parent as Toolbar
        myToolBar.setContentInsetsAbsolute(0,0)


        backImg = defaultActionBar.customView.findViewById(R.id.backImg)
        backImg.setOnClickListener {
            finish()
        }

    }
}