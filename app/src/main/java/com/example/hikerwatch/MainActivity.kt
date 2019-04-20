package com.example.hikerwatch

import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setLocationManager()
    }

    private fun setLocationManager(){
        var locationManager:LocationManager=LocationManager()
    }
}
