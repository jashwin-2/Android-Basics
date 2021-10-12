package com.example.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import kotlinx.android.synthetic.main.custom_layout.view.*

class CustomLayout(context: Context,attr : AttributeSet) : LinearLayout(context,attr) {

    init {
        inflate(getContext(),R.layout.custom_layout,this)
        app1.visibility = View.GONE
        app2.visibility = View.GONE
        app3.visibility = View.GONE

        app1.alpha= 0F
        app2.alpha= 0F
        app3.alpha= 0F

        viewTreeObserver.addOnPreDrawListener ( object : ViewTreeObserver.OnPreDrawListener{
            override fun onPreDraw(): Boolean {
                viewTreeObserver.removeOnPreDrawListener(this)
                ViewCompat.animate(app1).alpha(1F).duration = 500
                ViewCompat.animate(app2).alpha(1F).setStartDelay(120).duration = 500
                ViewCompat.animate(app3).alpha(1F).setStartDelay(240).duration = 500
                return false
            }
        }
        )
    }
    fun setUpView(){
        app1.text="App 1"
        app2.text="App 2"
        app3.text="App 3"
        app1.visibility=View.VISIBLE
        app2.visibility=View.VISIBLE
        app3.visibility=View.VISIBLE
    }
}