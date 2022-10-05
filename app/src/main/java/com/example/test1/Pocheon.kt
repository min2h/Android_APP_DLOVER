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
import kotlinx.android.synthetic.main.activity_paju.*
import kotlinx.android.synthetic.main.activity_pocheon.*
import kotlinx.android.synthetic.main.hwasung.*

class Pocheon : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pocheon)
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=543242%2C1178020%2C525497%2C1188480&rt1=%EB%B6%80%ED%8F%89%EA%B5%90&rt2=%EC%B6%95%EC%84%9D%EA%B5%90%EC%B0%A8%EB%A1%9C&rtIds=7907643%2C359748301&rtTypes=PLACE%2CPLACE"))
        po_map.setOnClickListener {
            startActivity(mIntent)
        }
        pocheon_ON.setOnClickListener {
            info.update("db_Favorite", FieldValue.arrayUnion("국립수목원로"))
            Toast.makeText(this, "즐겨찾기에 추가되었습니다", Toast.LENGTH_SHORT).show()
        }
        /*pocheon_OFF.setOnClickListener{
            val updates = hashMapOf<String, Any>(
                "db_Favorite" to FieldValue.delete()
            )
            info.update(updates)

        }
        */
    }
}
