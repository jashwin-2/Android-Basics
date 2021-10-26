package com.example.imageloading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val img ="https://media.istockphoto.com/photos/country-mailboxes-picture-id174699403?k=20&m=174699403&s=612x612&w=0&h=xNerKR5IQc4qk8LyJIa3Ztg4VmVCYTcPCtuZbfIjqXk="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).load(img).into(imageView)

        Glide.with(this)
            .load(img)
            .fitCenter()
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView2)

        Glide.with(this)
            .load(img)
            .override(300,400)
            .centerCrop()
            .error(R.drawable.ic_launcher_foreground)
            .into(imageView3)


    }

}