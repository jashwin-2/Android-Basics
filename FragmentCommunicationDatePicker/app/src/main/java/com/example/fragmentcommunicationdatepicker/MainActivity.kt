package com.example.fragmentcommunicationdatepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment: Fragment = HomeFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_container, fragment).commit()
        }
    }
}