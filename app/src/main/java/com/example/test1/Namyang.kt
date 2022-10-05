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
import kotlinx.android.synthetic.main.activity_namyang.*
import kotlinx.android.synthetic.main.hwasung.*

class Namyang : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_namyang)
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=567038%2C1121321%2C577562%2C1148234&rt1=%EC%A1%B0%EC%95%88%EB%A9%B4%EC%82%AC%EB%AC%B4%EC%86%8C&rt2=%EC%84%9C%ED%98%B8%EB%AF%B8%EC%88%A0%EA%B4%80&rtIds=8521783%2C7944359&rtTypes=PLACE%2CPLACE"))
        nam_map.setOnClickListener {
            startActivity(mIntent)
        }
        namyang_ON.setOnClickListener {
            info.update("db_Favorite", FieldValue.arrayUnion("화음길"))
            Toast.makeText(this, "즐겨찾기에 추가되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
