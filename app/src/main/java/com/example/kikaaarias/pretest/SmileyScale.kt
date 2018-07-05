package com.example.kikaaarias.pretest

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.media.MediaScannerConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.support.v4.app.NotificationCompat.getExtras
import android.support.v4.app.NotificationCompat.getExtras

class SmileyScale : AppCompatActivity() {

    private lateinit var imageButton1: ImageButton
    private lateinit var imageButton2: ImageButton
    private lateinit var imageButton3: ImageButton
    private lateinit var imageButton4: ImageButton
    private lateinit var imageButton5: ImageButton
    private lateinit var imageButton6: ImageButton

    private lateinit var imageButtonLayout: LinearLayout
    private lateinit var mainLayout: LinearLayout
    private lateinit var mainText: TextView
    private lateinit var currentFile: String
    private var questioncnt: Int = 0

    var usr = intent.extras.getString("USER")


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("debugging", "Reached onCreate")
        super.onCreate(savedInstanceState)
        setupUI()
    }

    private fun setupUI(){
        Log.i("debugging", "started setupUI")
//        writeExternalStorage(usr.toString())
        questioncnt++

        setContentView(R.layout.activity_smiley_scale)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        mainLayout = LinearLayout(this.applicationContext)
        imageButtonLayout = LinearLayout(this.applicationContext)

        mainText = TextView(this)
        mainText.text = ("Pick the face that best reflects your feelings right now.")
        mainText.setTextSize(TypedValue.COMPLEX_UNIT_SP,70f)
        mainText.setPadding(100, 0, 100, 0)
        mainText.gravity = Gravity.CENTER

        mainText.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800)

        Log.i("debugging", "mainText set")

        imageButton1 = ImageButton(this)
        imageButton2 = ImageButton(this)
        imageButton3 = ImageButton(this)
        imageButton4 = ImageButton(this)
        imageButton5 = ImageButton(this)
        imageButton6 = ImageButton(this)

        imageButton1.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton1.setImageResource(R.drawable.arousal1)
        imageButton1.setBackgroundColor(Color.TRANSPARENT)

        imageButton2.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton2.setImageResource(R.drawable.arousal2)
        imageButton2.setBackgroundColor(Color.TRANSPARENT)

        imageButton3.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton3.setImageResource(R.drawable.arousal3)
        imageButton3.setBackgroundColor(Color.TRANSPARENT)

        imageButton4.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton4.setImageResource(R.drawable.arousal4)
        imageButton4.setBackgroundColor(Color.TRANSPARENT)

        imageButton5.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton5.setImageResource(R.drawable.arousal5)
        imageButton5.setBackgroundColor(Color.TRANSPARENT)

        imageButton6.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton6.setImageResource(R.drawable.arousal6)
        imageButton6.setBackgroundColor(Color.TRANSPARENT)

        Log.i("debugging", "image buttons set")

        mainLayout.addView(mainText)
        mainLayout.addView(imageButtonLayout)

        imageButtonLayout.addView(imageButton6)
        imageButtonLayout.addView(imageButton5)
        imageButtonLayout.addView(imageButton4)
        imageButtonLayout.addView(imageButton3)
        imageButtonLayout.addView(imageButton2)
        imageButtonLayout.addView(imageButton1)

        imageButtonLayout.orientation = LinearLayout.HORIZONTAL
        mainLayout.orientation = LinearLayout.VERTICAL
        mainLayout.gravity = Gravity.CENTER

        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addContentView(mainLayout, lp)

        imageButton1.setOnClickListener{ onImageButton1() }
        imageButton2.setOnClickListener{ onImageButton2() }
        imageButton3.setOnClickListener{ onImageButton3() }
        imageButton4.setOnClickListener{ onImageButton4() }
        imageButton5.setOnClickListener{ onImageButton5() }
        imageButton6.setOnClickListener{ onImageButton6() }
        Log.i("debugging", "Successfully setup UI")
    }

    private fun onImageButton1(){
        writeExternalStorage("Q" + questioncnt + ": 1")
        buttonnormal()
    }

    private fun onImageButton2(){
        writeExternalStorage("Q" + questioncnt + ": 2")
        buttonnormal()
    }

    private fun onImageButton3(){
        writeExternalStorage("Q" + questioncnt + ": 3")
        buttonnormal()
    }

    private fun onImageButton4(){
        writeExternalStorage("Q" + questioncnt + ": 4")
        buttonnormal()
    }

    private fun onImageButton5(){
        writeExternalStorage("Q" + questioncnt + ": 5")
        buttonnormal()
    }

    private fun onImageButton6(){
        writeExternalStorage("Q" + questioncnt + ": 6")
        buttonnormal()
    }

    private fun buttonreset(){
        if (questioncnt == 2){
            imageButton1.setImageResource(R.drawable.valence1)
            imageButton2.setImageResource(R.drawable.valence2)
            imageButton3.setImageResource(R.drawable.valence3)
            imageButton4.setImageResource(R.drawable.valence4)
            imageButton5.setImageResource(R.drawable.valence5)
            imageButton6.setImageResource(R.drawable.valence6)
        }
    }

    private fun buttonnormal(){
        buttonreset()
        if (questioncnt <3){
            questioncnt++
        }
        else{
            finish()
        }
    }


    private fun writeExternalStorage(s: String){
        try {
            if (questioncnt == 0){
                currentFile = SimpleDateFormat("yyyyMMddHHmmss'.txt'").format(Date())
            }
            // Creates a file in the primary external storage space of the app
            // If the file does not exists, it is created.
            val testFile = File(this.getExternalFilesDir(null), currentFile)

            if (!testFile.exists())
                testFile.createNewFile()

            // Adds a line to the file
            val writer = BufferedWriter(FileWriter(testFile, true /*append*/))
            writer.write(s + "\n")
            writer.close()
            MediaScannerConnection.scanFile(this,
                    arrayOf(testFile.toString()), null, null)
        } catch (e: IOException) {
            Log.e("ReadWriteFile", "Unable to write to the currentFile file.")
        }
    }
    private fun showToast(message : String) = Toast.makeText(this@SmileyScale, message, Toast.LENGTH_SHORT).show()


}
