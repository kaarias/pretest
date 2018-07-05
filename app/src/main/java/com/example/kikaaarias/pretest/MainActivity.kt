package com.example.kikaaarias.pretest

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaScannerConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import java.io.*
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var button1: Button
    lateinit var button2: Button

    lateinit var buttonLayout: LinearLayout
    lateinit var mainLayout: LinearLayout
    lateinit var mainText: TextView
    val title: String = "Pre-Test!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    private fun setupUI(){
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        buttonLayout = LinearLayout(this.applicationContext)
        mainLayout = LinearLayout(this.applicationContext)

        mainText = TextView(this)
        mainText.text = title
        mainText.setTextSize(TypedValue.COMPLEX_UNIT_SP,70f)
        mainText.setPadding(100, 0, 100, 0)
        mainText.gravity = Gravity.CENTER
        mainText.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800)

        button1 = Button(this)
        button2 = Button(this)

        button1.layoutParams = LinearLayout.LayoutParams(800, 350)
        button1.text = "Parent"
        button1.setTextSize(TypedValue.COMPLEX_UNIT_SP,40f)
        button1.transformationMethod = null

        button2.layoutParams = LinearLayout.LayoutParams(800, 350)
        button2.text = "Child"
        button2.setTextSize(TypedValue.COMPLEX_UNIT_SP,40f)
        button2.transformationMethod = null

        mainLayout.addView(mainText)
        mainLayout.addView(buttonLayout)

        buttonLayout.addView(button1)
        buttonLayout.addView(button2)

        buttonLayout.orientation = LinearLayout.HORIZONTAL
        mainLayout.orientation = LinearLayout.VERTICAL
        mainLayout.gravity = Gravity.CENTER

        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addContentView(mainLayout, lp)

        button1.setOnClickListener{ onButton1() }
        button2.setOnClickListener{ onButton2() }

        button1.isEnabled = true
        button2.isEnabled = true
    }

    private fun onButton1(){
        val intent1 = Intent(this, ParentSurvey::class.java)
        startActivity(intent1)
    }

    private fun onButton2(){
        val intent2 = Intent(this, ChildSurvey::class.java)
        startActivity(intent2)
    }

    //just to make displaying toasts easier
    private fun showToast(message : String) = Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()

}
