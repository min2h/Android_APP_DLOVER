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
import kotlinx.android.synthetic.main.activity_gapeong.*
import kotlinx.android.synthetic.main.hwasung.*

class Gapeong : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gapeong)
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=583710%2C1155937%2C600238%2C1169891%2C615767%2C1180123%2C615783%2C1196021&rt1=%EA%B0%80%EC%9D%BC%EB%AF%B8%EC%88%A0%EA%B4%80&rt2=%EB%A6%AC%EB%B2%84%ED%8F%AC%EC%9D%B8%ED%8A%B8%EC%88%98%EC%83%81%EB%A0%88%EC%A0%80&rt3=%EA%B0%80%ED%8F%89%EA%B8%80%EB%9E%A8%ED%95%91%ED%8C%8C%ED%81%AC&rt4=%EB%82%A8%EC%9D%B4%EC%84%AC%EA%B0%80%ED%8F%89%EC%84%A0%EC%B0%A9%EC%9E%A5&rtIds=8676960%2C1202207311%2C20495150%2C8227032&rtTypes=PLACE%2CPLACE%2CPLACE%2CPLACE"))
        ga_map.setOnClickListener {
            startActivity(mIntent)
        }
        gapeong_ON.setOnClickListener {
            info.update("db_Favorite", FieldValue.arrayUnion("청평호반길,북한강변길"))
            Toast.makeText(this, "즐겨찾기에 추가되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
