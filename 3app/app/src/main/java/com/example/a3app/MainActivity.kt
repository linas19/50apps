package com.example.a3app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    var current = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText4.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus && editText4.text.toString() != "") {
                val cleanString = editText4.text.toString().replace("$", "");
                println(cleanString)
                val parsed = cleanString.toDouble()
                val formatted = NumberFormat.getCurrencyInstance().format((parsed));

                current = formatted;
                println(formatted)
                println(parsed)
                editText4.setText(formatted);
            }
        }
        seekBar.setProgress(15)
        tipPercentage.text = seekBar.progress.toString()
        seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {

                tipPercentage.text = seekBar.progress.toString()

                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })
    }
}
