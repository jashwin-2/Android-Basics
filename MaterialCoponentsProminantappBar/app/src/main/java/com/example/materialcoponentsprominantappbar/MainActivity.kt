package com.example.materialcoponentsprominantappbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.selectedItemId = R.id.more
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.search -> {
                    Toast.makeText(this,"search",Toast.LENGTH_SHORT).show()
                }
                R.id.more -> {
                    Toast.makeText(this,"More",Toast.LENGTH_SHORT).show()                }
            }
            true
        }
        val badge = bottomNavigation.getOrCreateBadge(R.id.more)
        badge.isVisible=true
        badge.number=10
    }


}