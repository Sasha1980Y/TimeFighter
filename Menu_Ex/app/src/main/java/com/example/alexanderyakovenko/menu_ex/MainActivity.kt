package com.example.alexanderyakovenko.menu_ex

import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var scoreTextView: TextView

    internal val TAG = MainActivity::class.java.simpleName


    internal var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        println("Temperature ${calculatetemperature(20.0)}")
        println("Temperature ${calculatetemperature(50.0)}")

        val intValue = "32".toInt()
        println("int value = $intValue")

        val intString = 32.toString()
        println("string = $intString")


        val fahrenheit =  32
        when (fahrenheit) {
            in 0..30 -> println("really cold")
            in 31..40 -> println("getting colder")
            in 41..50 -> println("kind of cold")
        }

        /*
        tapMeButton = findViewById<Button>(R.id.score_Button)
        scoreTextView = findViewById<TextView>(R.id.textView)

        tapMeButton.setOnClickListener {

            incrementScore()

        }*/

    }

    fun calculatetemperature(celsius: Double) : Double {
        return 9/5 * celsius + 32
    }




    @SuppressLint("StringFormatInvalid")
    private fun incrementScore() {
        score = score + 1

        Log.d(TAG,"On create $score")
        val newScore = "Your score : {score.toString()}"
        scoreTextView.text = score.toString()
    }


}
