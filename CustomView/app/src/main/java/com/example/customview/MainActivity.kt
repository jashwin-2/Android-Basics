package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emotionalFace.setOnClickListener {
            emotionalFace.happinessState = EmotionalFace.SAD
            emotionalFace.faceColor = R.color.black
        }
        sadButton.setOnClickListener {
            sadButton.happinessState = EmotionalFace.HAPPY
            sadButton.faceColor = R.color.purple_500
        }
        custom_layout.setUpView()
    }
}