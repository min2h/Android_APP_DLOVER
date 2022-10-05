package com.example.test1

import User_info
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ansan.*
import kotlinx.android.synthetic.main.frag_mypage.*
import kotlinx.android.synthetic.main.hwasung.*

class Ansan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ansan)
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=431487%2C1065538%2C406780%2C1053264&rt1=%EC%98%A4%EC%9D%B4%EB%8F%84%EA%B8%B0%EB%85%90%EA%B3%B5%EC%9B%90&rt2=%EB%8C%80%EB%B6%80%EB%8F%84%EA%B3%B5%EC%9B%90&rtIds=26957436%2C17384606&rtTypes=PLACE%2CPLACE"))
        ansan_map.setOnClickListener {
            startActivity(mIntent)
        }
        ansan_ON.setOnClickListener {
            info.update("db_Favorite", FieldValue.arrayUnion("시화방조제길"))
            Toast.makeText(this, "즐겨찾기에 추가되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
