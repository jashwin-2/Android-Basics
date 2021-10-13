package com.example.asynctask

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class IntentServiceClass : IntentService("MyService") {

    override fun onHandleIntent(intent: Intent?) {
        val name  = intent!!.getStringExtra("name")
        val handler = Handler(Looper.getMainLooper())

        for (i in 1..10){
            SystemClock.sleep(500)
            Log.d("Intent", "running $name in background: $i in ${Thread.currentThread().name}")
            Intent("IntentService").apply {
                putExtra("progress", i)
                LocalBroadcastManager.getInstance(this@IntentServiceClass).sendBroadcast(this)
            }
        }
        Intent("IntentService").apply {
            putExtra("status",true)
            LocalBroadcastManager.getInstance(this@IntentServiceClass).sendBroadcast(this)
        }
    }
}