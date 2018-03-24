package com.example.alexanderyakovenko.timefighter

import android.media.tv.TvInputManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView
    internal var score = 0
    internal var gameStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 20000
    internal val countDownInterval: Long = 1000
    internal val TAG = MainActivity::class.java.simpleName
    internal var timeLeftOnTimer: Long = 60000

    companion object {
        private val SCORE_KEY = "SCORE_KEY"
        private val TIME_LEFT_KEY = "TIME_LEFT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "omCreate called. Score is: $score")
        // add val to this class : //internal val TAG = MainActivity::class.java.simpleName


        tapMeButton = findViewById(R.id.tap_me_button)
        gameScoreTextView = findViewById(R.id.game_score__text_view)
        timeLeftTextView = findViewById(R.id.time_left_text_view)


        // animation
        val bounceAnimation = AnimationUtils.loadAnimation(this,R.anim.blick)
        gameScoreTextView.startAnimation(bounceAnimation)


        // change %s on 0
        //gameScoreTextView.text = getString(R.string.your_score, "0")
        //gameScoreTextView.text = getString(R.string.your_score, score.toString())

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeftOnTimer = savedInstanceState.getLong(TIME_LEFT_KEY)
            restoreGames()
        } else {
            resetGame()
        }

        tapMeButton.setOnClickListener { view ->

            // animation
            val bounceAnimation = AnimationUtils.loadAnimation(this,R.anim.bounce)
            view.startAnimation(bounceAnimation)
            incrementScore()
        }
    }

    private fun restoreGames() {
        gameScoreTextView.text = getString(R.string.your_score, score.toString())
        val restoredTime = timeLeftOnTimer / 1000
        timeLeftTextView.text = getString(R.string.time_left, restoredTime.toString())

        countDownTimer = object : CountDownTimer(timeLeftOnTimer, countDownInterval) {
            override fun onTick(p0: Long) {
                timeLeftOnTimer = p0
                val timeLeft = p0 / 1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())
            }

            override fun onFinish() {
                endGame()
            }
        }
        countDownTimer.start()
        gameStarted = false


    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        if (outState != null) {
            outState.putInt(SCORE_KEY, score)
        }
        if (outState != null) {
            outState.putLong(TIME_LEFT_KEY, timeLeftOnTimer)
        }
        countDownTimer.cancel()
        Log.d(TAG, "onSaveInstanceState: Saving Score: $score & Time left: $timeLeftOnTimer ")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy called.")
    }

    // first add menu resource (image for menu, if need add to drawable or find "info" default)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            if (item.itemId == R.id.action_about) {
                showInfo()
            }
        }
        return true
    }

    private fun showInfo() {
        val dialogTitle = getString(R.string.about_title, BuildConfig.VERSION_NAME)
        val dialogMessage = getString(R.string.about_message)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.create().show()
    }

    // end menu

    private  fun resetGame() {
        score = 0
        gameScoreTextView.text = getString(R.string.your_score, score.toString())
        val initialTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(R.string.time_left, initialTimeLeft.toString())

        // timer :  countDownTimer.start() and countDownTimer.cancel()
        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(p0: Long) {
                timeLeftOnTimer = p0
                val timeLeft = p0 / 1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())
            }

            override fun onFinish() {
                endGame()
            }
        }
        gameStarted = false
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {

        // alert in bottom screen
        Toast.makeText(this,getString(R.string.game_over_message, score.toString()), Toast.LENGTH_SHORT).show()
        resetGame()
    }

    private fun incrementScore() {
        if (!gameStarted) {
            startGame()
        }
        score = score + 1
        val newScore = getString(R.string.your_score, score.toString())
        gameScoreTextView.text = newScore
    }
}
