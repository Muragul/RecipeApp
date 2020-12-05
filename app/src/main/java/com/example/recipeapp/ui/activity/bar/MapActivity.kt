package com.example.recipeapp.ui.activity.bar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.recipeapp.R
import com.example.recipeapp.data.model.bar.Bar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : FragmentActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        for (bar in barListGenerator()) {
            val marker = LatLng(bar.latitude, bar.longitude)
            map.addMarker(MarkerOptions().position(marker).title(bar.title))
            map.moveCamera(CameraUpdateFactory.newLatLng(marker))
        }
        map.uiSettings.isZoomControlsEnabled = true
        map.setMinZoomPreference(12F)
        progressBar.visibility = View.GONE
    }

    private fun barListGenerator(): List<Bar> {
        val bar = Bar(
            1,
            "Jungle",
            "Lounge bar Jungle in Almaty, Markov 61",
            43.225906,
            76.935647
        )
        val bar1 = Bar(
            2,
            "Friends bar & terrace",
            "Seifullin 617",
            43.233298,
            76.935089
        )
        val bar2 = Bar(
            3,
            "William Lawson`s 13 The Bar",
            "Bogenbai batyr 102",
            43.253653,
            76.954647
        )
        val bar3 = Bar(
            4,
            "BarFly",
            "Dostyk 52",
            43.24495,
            76.956976
        )
        val bar4 = Bar(
            5,
            "BARSTOL & KOK",
            "Jeltoksan 126",
            43.252175,
            76.940158
        )
        val bar5 = Bar(
            6,
            "COCOS RESTOBAR",
            "Kazhymukana 49",
            43.225636,
            76.95443
        )
        val bar6 = Bar(
            7,
            "Fahrenheit Bar & Grill",
            "Abylaikhana 96",
            43.248353,
            76.942383
        )
        val bar7 = Bar(
            8,
            "MansArda",
            "Shokai 37",
            43.271766,
            76.984529
        )
        val list: MutableList<Bar> = ArrayList()
        list.add(bar)
        list.add(bar1)
        list.add(bar2)
        list.add(bar4)
        list.add(bar5)
        list.add(bar6)
        list.add(bar7)
        list.add(bar3)
        return list
    }
}