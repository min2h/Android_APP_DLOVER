package com.example.test1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class List_CustomAdapter(val context: Context, val DataList: ArrayList<String>): BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view : View = LayoutInflater.from(context).inflate(R.layout.course_list_item, null)
            val name = view.findViewById<TextView>(R.id.course_item)
            val data = DataList?.get(position)
            name.text = data
            return view
        }
        override fun getItem(position: Int): String? = DataList?.get(position)
        override fun getItemId(position: Int): Long = 1L
        override fun getCount(): Int = DataList?.size!!
}