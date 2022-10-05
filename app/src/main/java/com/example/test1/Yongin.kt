package com.example.test1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.activity_yongin.*

class Yongin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yongin)
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=543227%2C1054083%2C540215%2C1057920&rt1=%EA%B0%80%EC%8B%A4%EB%B2%9A%EA%BD%83%EA%B8%B8&rt2=%EB%B0%B1%EB%A0%A8%EC%82%AC&rtIds=19566713%2C26103109&rtTypes=PLACE%2CPLACE"))
        yong_map.setOnClickListener {
            startActivity(mIntent)
        }
        yongin_ON.setOnClickListener {
            info.update("db_Favorite", FieldValue.arrayUnion("가실벚꽃길"))
            Toast.makeText(this, "즐겨찾기에 추가되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
