package com.example.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // val dataGenerator = DataGenerator()
        val viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        val number: LiveData<String>? = viewModel.getNumber()

        viewModel.randomNumber?.observe(this, Observer { t -> textView.text=t })
        textView.text = number.toString()
        button.setOnClickListener{
                viewModel.createNumber()
        }
    }
}