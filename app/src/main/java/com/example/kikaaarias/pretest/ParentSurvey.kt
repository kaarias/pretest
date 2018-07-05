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

class ParentSurvey : AppCompatActivity() {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button

    private lateinit var imageButton1: ImageButton
    private lateinit var imageButton2: ImageButton
    private lateinit var imageButton3: ImageButton
    private lateinit var imageButton4: ImageButton
    private lateinit var imageButton5: ImageButton
    private lateinit var imageButton6: ImageButton

    private lateinit var buttonLayout: LinearLayout
    private lateinit var imageButtonLayout: LinearLayout
    private lateinit var mainLayout: LinearLayout
    private lateinit var mainText: TextView
    private lateinit var currentFile: String
    private var questioncnt: Int = 0

    private lateinit var smfq: List<String>
    private var pt1: SMFQ = SMFQ()

    private var pt2: CPRS = CPRS()
    private lateinit var cprs: List<String>

    private var pt3: PANASc = PANASc()
    private lateinit var panasc: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    private fun setupUI(){
        writeExternalStorage("Parent")
        questioncnt++
        smfq = pt1.smfq_parent
        cprs = pt2.cprs_p
        panasc = pt3.panas_c

        setContentView(R.layout.activity_parent_survey)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        buttonLayout = LinearLayout(this.applicationContext)
        mainLayout = LinearLayout(this.applicationContext)
        imageButtonLayout = LinearLayout(this.applicationContext)

        mainText = TextView(this)
        mainText.text = smfq[0]
        mainText.setTextSize(TypedValue.COMPLEX_UNIT_SP,70f)
        mainText.setPadding(100, 0, 100, 0)
        mainText.gravity = Gravity.CENTER

        mainText.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800)

        button1 = Button(this)
        button2 = Button(this)
        button3 = Button(this)
        button4 = Button(this)
        button5 = Button(this)

        imageButton1 = ImageButton(this)
        imageButton2 = ImageButton(this)
        imageButton3 = ImageButton(this)
        imageButton4 = ImageButton(this)
        imageButton5 = ImageButton(this)
        imageButton6 = ImageButton(this)

        button1.layoutParams = LinearLayout.LayoutParams(800, 350)
        button1.text = "Not True"
        button1.setTextSize(TypedValue.COMPLEX_UNIT_SP,40f)
        button1.transformationMethod = null

        button2.layoutParams = LinearLayout.LayoutParams(800, 350)
        button2.text = "Sometimes"
        button2.setTextSize(TypedValue.COMPLEX_UNIT_SP,40f)
        button2.transformationMethod = null

        button3.layoutParams = LinearLayout.LayoutParams(800, 350)
        button3.text = "True"
        button3.setTextSize(TypedValue.COMPLEX_UNIT_SP,40f)
        button3.transformationMethod = null

        button4.layoutParams = LinearLayout.LayoutParams(400, 300)
        button4.text = "Applies Somewhat"
        button4.setTextSize(TypedValue.COMPLEX_UNIT_SP,15f)
        button4.transformationMethod = null

        button5.layoutParams = LinearLayout.LayoutParams(400, 300)
        button5.text = "Definitely Applies"
        button5.setTextSize(TypedValue.COMPLEX_UNIT_SP,15f)
        button5.transformationMethod = null

        imageButton1.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton1.setImageResource(R.drawable.arousal1);
        imageButton1.setBackgroundColor(Color.TRANSPARENT)

        imageButton2.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton2.setImageResource(R.drawable.arousal2);
        imageButton2.setBackgroundColor(Color.TRANSPARENT)

        imageButton3.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton3.setImageResource(R.drawable.arousal3);
        imageButton3.setBackgroundColor(Color.TRANSPARENT)

        imageButton4.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton4.setImageResource(R.drawable.arousal4);
        imageButton4.setBackgroundColor(Color.TRANSPARENT)

        imageButton5.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton5.setImageResource(R.drawable.arousal5);
        imageButton5.setBackgroundColor(Color.TRANSPARENT)

        imageButton6.layoutParams = LinearLayout.LayoutParams(400, 400)
        imageButton6.setImageResource(R.drawable.arousal6);
        imageButton6.setBackgroundColor(Color.TRANSPARENT)

        mainLayout.addView(mainText)
        mainLayout.addView(buttonLayout)

        buttonLayout.addView(button1)
        buttonLayout.addView(button2)
        buttonLayout.addView(button3)

        imageButtonLayout.addView(imageButton6)
        imageButtonLayout.addView(imageButton5)
        imageButtonLayout.addView(imageButton4)
        imageButtonLayout.addView(imageButton3)
        imageButtonLayout.addView(imageButton2)
        imageButtonLayout.addView(imageButton1)

        buttonLayout.orientation = LinearLayout.HORIZONTAL
        imageButtonLayout.orientation = LinearLayout.HORIZONTAL
        mainLayout.orientation = LinearLayout.VERTICAL
        mainLayout.gravity = Gravity.CENTER

        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addContentView(mainLayout, lp)

        button1.setOnClickListener{ onButton1() }
        button2.setOnClickListener{ onButton2() }
        button3.setOnClickListener{ onButton3() }
        button4.setOnClickListener{ onButton4() }
        button5.setOnClickListener{ onButton5() }

        imageButton1.setOnClickListener{ onImageButton1() }
        imageButton2.setOnClickListener{ onImageButton2() }
        imageButton3.setOnClickListener{ onImageButton3() }
        imageButton4.setOnClickListener{ onImageButton4() }
        imageButton5.setOnClickListener{ onImageButton5() }
        imageButton6.setOnClickListener{ onImageButton6() }

        button4.isEnabled = false
        button5.isEnabled = false
    }

    fun onButton1(){
        //Not True, Definitely does not apply
        writeExternalStorage("Q" + questioncnt + ": 1")
        buttonnormal()
    }

    fun onButton2(){
        //Sometimes, Not really
        writeExternalStorage("Q" + questioncnt + ": 2")
        buttonnormal()
    }

    fun onButton3(){
        //True, Neutral not sure
        writeExternalStorage("Q" + questioncnt + ": 3")
        buttonnormal()
    }

    fun onButton4(){
        //Applies somewhat
        writeExternalStorage("Q" + questioncnt + ": 4")
        buttonnormal()
    }

    fun onButton5(){
        //Definitely Applies
        writeExternalStorage("Q" + questioncnt + ": 5")
        buttonnormal()
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
        if (questioncnt == 13){
            mainText.setTextSize(TypedValue.COMPLEX_UNIT_SP,55f)

            buttonLayout.addView(button4)
            buttonLayout.addView(button5)

            button4.isEnabled = true
            button5.isEnabled = true

            button1.text = "Definitely Does Not Apply"
            button2.text = "Not\nReally"
            button3.text = "Neutral, Not Sure"

            button1.setTextSize(TypedValue.COMPLEX_UNIT_SP,15f)
            button2.setTextSize(TypedValue.COMPLEX_UNIT_SP,15f)
            button3.setTextSize(TypedValue.COMPLEX_UNIT_SP,15f)

            button1.layoutParams = LinearLayout.LayoutParams(400, 300)
            button2.layoutParams = LinearLayout.LayoutParams(400, 300)
            button3.layoutParams = LinearLayout.LayoutParams(400, 300)
        }
        else if (questioncnt == 43){
            mainLayout.removeView(buttonLayout)
            mainLayout.addView(imageButtonLayout)
        }
        else if (questioncnt == 44){
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
        if (questioncnt<=12) {
            mainText.text = smfq[questioncnt]
        }
        else if (questioncnt <= 42){
            mainText.setText(cprs[questioncnt-13])
        }
        else if (questioncnt <= 44){
            mainText.setText("Pick the face that best reflects your feelings right now.")
        }
        else{
            finish()
        }
        questioncnt++
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

            MediaScannerConnection.scanFile(this, arrayOf(testFile.toString()), null, null)
        }
        catch (e: IOException) {
            Log.e("ReadWriteFile", "Unable to write to the currentFile file.")
        }
    }

    //just to make displaying toasts easier
    private fun showToast(message : String) = Toast.makeText(this@ParentSurvey, message, Toast.LENGTH_SHORT).show()

}