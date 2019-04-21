package com.example.hikerwatch

import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(ContextCompat.checkSelfPermission(this@MainActivity,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this@MainActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)

        }else{
            ActivityCompat.requestPermissions(
                this@MainActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
        initString()
        setLocationManager()

    }
    private fun initString(){
        tvLongitude.text="Longitude".capitalize()
        tvLatitude.text="Latitude".capitalize()
        tvAccuracy.text="Accuracy".capitalize()
        tvAltitude.text="Altitude".capitalize()
        tvAddress.text="Address".capitalize()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                Log.d("String","permission")
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
            } else {
                Log.d("String","permission")
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun setLocationManager() {
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener=object :LocationListener{
            override fun onLocationChanged(location: Location?) {
                tvLatitudeVal.text=location?.latitude.toString()
                tvLongitudeVal.text=location?.longitude.toString()
                tvAccuracyVal.text=location?.accuracy.toString()
                tvAltitudeVal.text=location?.altitude.toString()
                var geoCoder= Geocoder(this@MainActivity,Locale.getDefault())
                var addressList=geoCoder.getFromLocation(location!!.latitude,location.longitude,1)
                var address=""
                address+=addressList[0].adminArea
                address+=addressList[0].countryName
                tvAddressVal.text=address
                Log.d("String",location?.latitude.toString())

            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }
        }

    }


}
