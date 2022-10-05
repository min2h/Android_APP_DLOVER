package com.example.test1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.custom_list.view.*
import java.util.*


class Data(val profile:Int, val name:String)

class CustomViewHolder(v : View) : RecyclerView.ViewHolder(v) {
    val profile = v.iv_custom
    val name = v.tv_custom
}

class CustomAdapter(val DataList:ArrayList<Data>, val context:Context) : RecyclerView.Adapter<CustomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val cellForRow = LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount() = DataList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val curData = DataList[position]
        holder.profile.setImageResource(curData.profile)
        holder.name.text = curData.name

        holder.itemView.setOnClickListener{
            if(curData.name=="화성방조제길")
            {val intent = Intent( context, Hwasung::class.java)
                context.startActivity(intent)}
            else if(curData.name=="자유로") {
                run {
                    val intent = Intent(context, Paju::class.java)
                    context.startActivity(intent)
                }
            }
            else if(curData.name=="국립수목원로") {
                run {
                    val intent = Intent(context, Pocheon::class.java)
                    context.startActivity(intent)
                }
            }
            else if(curData.name=="청평호반길,북한강변길") {
                run {
                    val intent = Intent(context, Gapeong::class.java)
                    context.startActivity(intent)
                }
            }
            else if(curData.name=="남한산성,팔당호 벚꽃길") {
                run {
                    val intent = Intent(context, Gwangju::class.java)
                    context.startActivity(intent)
                }
            }
            else if(curData.name=="화음길") {
                run {
                    val intent = Intent(context, Namyang::class.java)
                    context.startActivity(intent)
                }
            }
            else if(curData.name=="두물머리 강변길") {
                run {
                    val intent = Intent(context, Yangpyeong::class.java)
                    context.startActivity(intent)
                }
            }
            else if(curData.name=="가실벚꽃길") {
                run {
                    val intent = Intent(context, Yongin::class.java)
                    context.startActivity(intent)
                }
            }
            else if(curData.name=="금광호수로") {
                run {
                    val intent = Intent(context, Ansung::class.java)
                    context.startActivity(intent)
                }
            }
            else if(curData.name=="시화방조제길") {
                run {
                    val intent = Intent(context, Ansan::class.java)
                    context.startActivity(intent)
                }
            }
            var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
            val user = Firebase.auth.currentUser
            if (user != null) {
                uid = user.uid
            }
            var db = Firebase.firestore
            var info = db.collection("user").document(uid)
            info.update("db_Course", FieldValue.arrayUnion(curData.name))
        }
    }
}