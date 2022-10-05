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
import kotlinx.android.synthetic.main.activity_yangpyeong.*

class Yangpyeong : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yangpyeong)
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=570568%2C1124762%2C587545%2C1139923&rt1=%EC%96%91%EC%88%98%EB%A6%AC%ED%99%98%EA%B2%BD%EC%83%9D%ED%83%9C%EA%B3%B5%EC%9B%90&rt2=%EB%8F%84%EC%9E%A5%EA%B3%84%EA%B3%A1%EC%9C%A0%EC%9B%90%EC%A7%80&rtIds=18090705%2C12618422&rtTypes=PLACE%2CPLACE"))
        yang_map.setOnClickListener {
            startActivity(mIntent)
        }
        yangpyeong_ON.setOnClickListener {
            info.update("db_Favorite", FieldValue.arrayUnion("두물머리 강변길"))
            Toast.makeText(this, "즐겨찾기에 추가되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
