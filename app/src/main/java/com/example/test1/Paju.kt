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
import kotlinx.android.synthetic.main.activity_paju.*
import kotlinx.android.synthetic.main.frag_mypage.*
import kotlinx.android.synthetic.main.hwasung.*
class Paju : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paju)
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=431446%2C1173982%2C447499%2C1212529&rt1=%EB%AC%B8%EB%B0%9CIC&rt2=%EB%8B%B9%EB%8F%99IC&rtIds=10417432%2C10265299&rtTypes=PLACE%2CPLACE"))
        pa_map.setOnClickListener {
            startActivity(mIntent)
        }
        paju_ON.setOnClickListener {
            info.update("db_Favorite", FieldValue.arrayUnion("자유로"))
            Toast.makeText(this, "즐겨찾기에 추가되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
