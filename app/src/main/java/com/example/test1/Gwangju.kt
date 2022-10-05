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
import kotlinx.android.synthetic.main.activity_gwangju.*

class Gwangju : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gwangju)
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=540353%2C1104320%2C566993%2C1111241&rt1=%EB%82%A8%ED%95%9C%EC%82%B0%EC%84%B1%EB%B2%9A%EA%BD%83%EA%B8%B8&rt2=%EA%B2%BD%EA%B8%B0%EB%8F%84%EC%88%98%EC%9E%90%EC%9B%90%EB%B3%B8%EB%B6%80&rtIds=284081346%2C11338785&rtTypes=PLACE%2CPLACE"))
        gwang_map.setOnClickListener {
            startActivity(mIntent)
        }
        gwangju_ON.setOnClickListener {
            info.update("db_Favorite", FieldValue.arrayUnion("남한산성,팔당호 벚꽃길"))
            Toast.makeText(this, "즐겨찾기에 추가되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
