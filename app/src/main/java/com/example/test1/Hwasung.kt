package com.example.test1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.hwasung.*

class Hwasung : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hwasung)
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=447399%2C985901%2C429369%2C1004757&rt1=%EB%A7%A4%ED%96%A5%EB%A6%AC%EC%97%AD%EC%82%AC%EA%B8%B0%EB%85%90%EA%B4%80&rt2=%EA%B6%81%ED%8F%89%ED%95%AD&rtIds=766768297%2C14973250&rtTypes=PLACE%2CPLACE"))
        hwa_map.setOnClickListener {
            startActivity(mIntent)
        }
        hwasung_ON.setOnClickListener {
            info.update("db_Favorite", FieldValue.arrayUnion("화성방조제길"))
            Toast.makeText(this, "즐겨찾기에 추가되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
}