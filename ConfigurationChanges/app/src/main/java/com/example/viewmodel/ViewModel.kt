package com.example.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
     var randomNumber : MutableLiveData<String>? = null
     fun getNumber(): MutableLiveData<String>? {
        if(randomNumber==null) {
            randomNumber = MutableLiveData()
            createNumber()
        }
        return randomNumber
    }

    public fun createNumber() {
        randomNumber?.value = (1..10).random().toString()
    }
}