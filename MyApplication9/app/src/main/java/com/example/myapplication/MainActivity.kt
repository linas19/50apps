package com.example.myapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*

import android.os.Handler


class MainActivity : AppCompatActivity() {
    var count = 0
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        textView.text = count.toString()
        mHandler =  Handler()
        button2.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> startRepeatingTask()
                    MotionEvent.ACTION_UP -> stopRepeatingTask()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })
    }

    var mStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                increaseCount() //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler?.postDelayed(this, 500)
            }
        }
    }

    fun startRepeatingTask() {
        mStatusChecker.run()
    }

    fun stopRepeatingTask() {
        mHandler?.removeCallbacks(mStatusChecker)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> resetClicked()
            else -> super.onOptionsItemSelected(item)
        }
            }
    fun buttonClicked(view: View){
        increaseCount()

    }
    fun resetClicked():Boolean{
        count = 0
        textView.text = count.toString()
        return true
    }
    fun increaseCount(){
        count += 1
        textView.text = count.toString()
    }

}
