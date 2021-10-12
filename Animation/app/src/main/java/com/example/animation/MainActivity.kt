package com.example.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            AnimationUtils.loadAnimation(this,R.anim.viewanimation).apply {
                duration = 1000
                text_view.startAnimation(this)
            }
        }
        val transition = ObjectAnimator.ofFloat(text_view,"translationX",100f).apply {
            duration = 1000
        }
        val rotation = ObjectAnimator.ofFloat(text_view,"rotation",180f).apply {
            duration = 1000
        }
        val anim = AnimatorSet().apply{
            play(transition).with(rotation)
        }
        button2.setOnClickListener{
           AnimatorSet().apply {
               duration=4000
               play(anim)
               start()
           }
        }
        setUpWindowAnimation()
        btn_nxt_activity.setOnClickListener{

            Intent(this,Activity2 :: class.java).also {
                startActivity(it,ActivityOptions.makeSceneTransitionAnimation(this,icon,
                    "common_icon").toBundle())
            }
        }
    }

    private fun setUpWindowAnimation() {
        val slide  = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide)
        window.exitTransition = slide
    }
}