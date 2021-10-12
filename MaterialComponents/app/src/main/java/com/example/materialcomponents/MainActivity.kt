package com.example.materialcomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomAppBar.setNavigationOnClickListener{
            Toast.makeText(this,"Navigation icon clicked",Toast.LENGTH_SHORT).show()
        }
        floatingIcon.setOnClickListener{
            Toast.makeText(this,"Floating icon clicked",Toast.LENGTH_SHORT).show()

        }
        bottomAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.search -> Toast.makeText(this,"Search icon clicked",Toast.LENGTH_SHORT).show()
                R.id.more ->Toast.makeText(this,"More icon clicked",Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}