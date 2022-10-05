package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*

class GoogleMap : AppCompatActivity(), OnMapReadyCallback {

    val permission = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    val PERM_FLAG = 99

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_map)

        if(isPermitted()) {
            startProcess()
        } else {
            ActivityCompat.requestPermissions(this, permission, PERM_FLAG)
        }

    }

    fun isPermitted() : Boolean {
        for(perm in permission) {
            if(ContextCompat.checkSelfPermission(this, perm) != PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun startProcess() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setUpdateLocationListner()
    }

    // 내 위치를 가져오는 코드
    lateinit var fusedLocationClient:FusedLocationProviderClient
    lateinit var locationCallback:LocationCallback

     fun setUpdateLocationListner(){
         val locationRequest = LocationRequest.create()
         locationRequest.run{
             priority = LocationRequest.PRIORITY_HIGH_ACCURACY
             interval = 1000
         }

         locationCallback = object : LocationCallback() {
             override fun onLocationResult(locationResult: LocationResult?) {
                 locationResult?.let {
                     for((i, location) in it.locations.withIndex()){
                         Log.d("로케이션", "$i ${location.latitude}, ${location.longitude}")
                         setLastLocation(location)
                     }
                 }
             }
         }

         fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
     }
    
    fun setLastLocation(location : Location) {
        val myLocation = LatLng(location.latitude, location.longitude)
        val marker = MarkerOptions()
            .position(myLocation)
            .title("I'm here!")
        val cameraOption = CameraPosition.Builder()
            .target(myLocation)
            .zoom(17f)
            .build()
        val camera = CameraUpdateFactory.newCameraPosition(cameraOption)
        mMap.clear()

        mMap.addMarker(marker)
        mMap.moveCamera(camera)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            PERM_FLAG -> {
                var check = true
                for(grant in grantResults) {
                    if(grant != PERMISSION_GRANTED) {
                        check = false
                        break
                    }
                }
                if(check) {
                    startProcess()
                } else {
                    Toast.makeText(this, "권한을 승인해야지만 실행시킬 수 있습니다.", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }


}
