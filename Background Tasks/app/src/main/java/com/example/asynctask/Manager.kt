package com.example.asynctask

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class Manager {
    private val workQueue = LinkedBlockingQueue<Runnable>()
    val threadPoolExecutor = ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
        KEEP_ALIVE_TIME.toLong(),TimeUnit.MILLISECONDS,workQueue)
    companion object{
        val CORE_POOL_SIZE = 5
        val MAX_POOL_SIZE = 10
        val KEEP_ALIVE_TIME = 50
    }
    fun runTask(runnable : Runnable){
        threadPoolExecutor.execute(runnable)
    }
}
