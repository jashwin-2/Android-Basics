package com.example.fragmentcommunicationdatepicker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_home.*
import android.app.Activity

import android.content.Intent







class HomeFragment : Fragment(R.layout.fragment_home) {
    private val  REQUEST_CODE = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            btn_select_date.setOnClickListener {
                val newFragment = DatePickerFragment()

                newFragment.setTargetFragment(this@HomeFragment, REQUEST_CODE)

                newFragment.show((activity as AppCompatActivity?)!!.supportFragmentManager, "datePicker")

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val selectedDate = data!!.getStringExtra("selectedDate")

            tv_selected_date.text = selectedDate
        }
    }


}

