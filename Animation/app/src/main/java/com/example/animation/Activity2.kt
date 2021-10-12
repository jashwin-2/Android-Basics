package com.example.animation

import android.app.ActivityOptions
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionInflater
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        setUpWindowAnimation()
        button3.setOnClickListener{

        }
    }
    private fun setUpWindowAnimation() {
        val fade : Fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade) as Fade
        window.enterTransition = fade
    }
}