package com.example.test1

import User_info
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.signup_page.*

class Signup : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)
        auth = Firebase.auth
        sign_complete.setOnClickListener {
            createAccount(Make_id.text.toString(),Make_password.text.toString())
        }
    }

    private fun createAccount(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, "계정을 생성했습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
                        val user = Firebase.auth.currentUser
                        if (user != null) {
                            uid = user.uid
                        }
                        var db = Firebase.firestore
                        val first = User_info(null,null,null,null,null)
                        db.collection("user").document(uid).set(first)
                        finish() // 가입창 종료
                    } else {
                        Toast.makeText(
                            this, "형식을 다시 한번 확인해주세요",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}