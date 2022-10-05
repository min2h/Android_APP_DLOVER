package com.example.test1

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.my_course.*


class My_Course : AppCompatActivity() {
    var List = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_course)
        List = intent.getSerializableExtra("array") as ArrayList<String>
        if(List != null) {
            List.reverse()
            val adapter = List_CustomAdapter(this, List)
            course_list.adapter = adapter
        }
    }
}
