package com.example.test1

import User_info
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.profile_edit.*
import java.io.ByteArrayOutputStream


class Profile_edit : AppCompatActivity() {
    private val GALLERY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_edit)
        val storage = Firebase.storage
        val storageRef = storage.reference
        var uid : String = ""
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        val db = Firebase.firestore // 파이어 베이스 이름, 나이, 차종 저장하기 위해 만든 변수
        val mountainsRef = storageRef.child(uid)
        val mountainImagesRef = storageRef.child(uid)
        var info = db.collection("user").document(uid)
        info.get().addOnSuccessListener { documentSnapshot ->
            var data = documentSnapshot.toObject<User_info>()
            if(data?.DB_Name != null){
                Edit_Name.setText(data?.DB_Name)
            }
            if(data?.DB_Age != null){
                Edit_Age.setText(data?.DB_Age)
            }
            if(data?.DB_Car != null){
                Edit_Car.setText(data?.DB_Car)
            }
        }
        mountainsRef.name == mountainImagesRef.name
        mountainsRef.path == mountainImagesRef.path
        var islandRef = storageRef.child(uid)
        val ONE_MEGABYTE: Long = 1024 * 1024
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            var bitmap : Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            Edit_Photo.setImageBitmap(bitmap)
        }
        var name : String = ""
        var age : String = ""
        var car : String = ""
        save.setOnClickListener {
            //user id를 통해 사용자의 정보를 구분하기 위해서 저장
            val bitmap = (Edit_Photo.getDrawable() as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            val scale = (1024 / bitmap.width.toFloat())
            val image_w = (bitmap.width * scale).toInt()
            val image_h = (bitmap.height * scale).toInt()
            val resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true)
            resize.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()
            mountainsRef.putBytes(byteArray)
            info.update("db_Name", name)
            info.update("db_Age", age)
            info.update("db_Car", car)
            //setResult(Activity.RESULT_OK, nextIntent)
            finish()
        }
        Edit_Photo.setOnClickListener{
            val intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent,GALLERY)
        }
        Edit_Name.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                var temp : String = Edit_Name.text.toString()
                name = temp
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        Edit_Age.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                var temp : String = Edit_Age.text.toString()
                age = temp
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        Edit_Car.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                var temp : String = Edit_Car.text.toString()
                car = temp
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY) {
                var ImnageData: Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImnageData)
                    Edit_Photo.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}