package com.amldev.clustersmap

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.UiSettings

/**
 * Created by Peter-John on 2017-04-11.
 * GoogleMapsTutorial
 */

abstract class BaseGoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        createGoogleMap()
    }

    protected val layoutId: Int
        get() = R.layout.activity_main

    private fun createGoogleMap() {
        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)
    }

    protected fun setupMap(mMap: GoogleMap?) {
        if (mMap != null) {
            mMap.isBuildingsEnabled = true
            mMap.isIndoorEnabled = true
            mMap.isTrafficEnabled = true
            val mUiSettings: UiSettings= mMap.uiSettings
            mUiSettings.isZoomControlsEnabled = true
            mUiSettings.isCompassEnabled = true
            mUiSettings.isMyLocationButtonEnabled = true
            mUiSettings.isScrollGesturesEnabled = true
            mUiSettings.isZoomGesturesEnabled = true
            mUiSettings.isTiltGesturesEnabled = true
            mUiSettings.isRotateGesturesEnabled = true
            // permissions
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            mMap.isMyLocationEnabled = true
        }

    }

    override fun onResume() {
        super.onResume()
        //createGoogleMap();
    }
}
