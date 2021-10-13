package com.example.asynctask

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.widget.ProgressBar
import android.widget.TextView

class Task(
    private val context: Context, val taskName: String, private val textView: TextView
    , private val progressBar: ProgressBar) : Runnable {
    override fun run() {
        for (i in 1..10){
            (context as Activity).runOnUiThread{
                textView.text = "${i*10} %"
                progressBar.progress = i
            }
            SystemClock.sleep(500)
        }
    }
}