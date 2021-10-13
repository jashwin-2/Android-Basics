package com.example.asynctask

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var broadcastReceiver : BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, intent: Intent?) {
                val progress = intent!!.getIntExtra("progress",0)
                progressBar.progress = progress
                val isFinished = intent.getBooleanExtra("status",false)
                if(isFinished)
                {
                    textView.text = "Upload Completed"
                    progressBar2.visibility = View.GONE
                }
            }
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(
            broadcastReceiver, IntentFilter("IntentService")
        )

        progressBar2.visibility = View.GONE

        button.setOnClickListener {
            textView.text = ""
            UploadTask().execute("Uploading Task")
        }

        //intent service
        intent_service.setOnClickListener {
            textView.text = ""
            Intent(this, IntentServiceClass::class.java).apply {
                putExtra("name", "Upload Task")
                progressBar2.visibility = View.VISIBLE
                startService(this)
            }
        }

        thread_pool.setOnClickListener{
            Intent(this,ThreadPool::class.java).apply {
                startActivity(this)
            }
        }

    }

    inner class UploadTask : AsyncTask<String, Int, String>() {
        override fun onPreExecute() {
            textView.text = "Uploading  ...."
            button.isEnabled = false
            progressBar2.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: String?): String {
            for (i in 1..10) {
                publishProgress(i)
                SystemClock.sleep(500)
                Log.d(
                    "Upload",
                    "running ${p0[0]} in background: $i in ${Thread.currentThread().name}"
                )
            }
            return "Task Completed"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressBar2.visibility = View.GONE
            textView.text = "Upload Completed"
            button.isEnabled = true
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressBar.progress = values[0]!!
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }
}
