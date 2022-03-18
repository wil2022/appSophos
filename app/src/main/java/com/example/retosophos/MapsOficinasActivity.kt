package com.example.retosophos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.retosophos.databinding.ActivityMapsOficinasBinding

class MapsOficinasActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsOficinasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsOficinasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // methods to control the operations that will
    // happen when user clicks on the action buttons
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, ActivityMenu::class.java)
        val intent2 = Intent(this, EnvioDocsActivity::class.java)
        val intent3 = Intent(this, VerDocsActivity::class.java)
        val intent4 = Intent(this, MapsOficinasActivity::class.java)
        val intent5 = Intent(this, MainActivity::class.java)

        when (item.itemId) {
            R.id.menu_ppal -> {
                startActivity(intent)
            }
            R.id.enviar_doc -> {
                startActivity(intent2)
            }
            R.id.ver_docs -> {
                startActivity(intent3)
            }
            R.id.oficinas -> {
                startActivity(intent4)
            }
            R.id.cerrar_sesion -> {
                startActivity(intent5)
            }

        }
        return super.onOptionsItemSelected(item)
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.setZoomControlsEnabled(true)

        mMap.uiSettings.isMyLocationButtonEnabled()


        val bogota = LatLng(4.6796679999999995, -74.044757)
        mMap.addMarker(MarkerOptions().position(bogota).title("Edificio Davivienda - Piso 4"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bogota, 10F))

        val bogota2 = LatLng(4.612525050021438, -74.06940610000004)
        mMap.addMarker(MarkerOptions().position(bogota2).title("Edificio Tequendama - Piso 30"))

        val medellin = LatLng(6.218229100000025, -75.58021739999998)
        mMap.addMarker(MarkerOptions().position(medellin).title("CEOH - 107"))

        val medellin2 = LatLng(6.224464299999994, -75.57474939999997)
        mMap.addMarker(MarkerOptions().position(medellin2).title("Ciudad del Rio - 1009"))

        val chile = LatLng(-33.440570099999995, -70.64851440000001)
        mMap.addMarker(MarkerOptions().position(chile).title("Agustinas 833 – Piso 10"))

        val estados_unidos = LatLng(40.7504055, -73.98382049999998)
        mMap.addMarker(MarkerOptions().position(estados_unidos).title("404 Fifth Avenue Tenant LLC"))

        val panama = LatLng(9.005743200000007, -79.5875616999999)
        mMap.addMarker(MarkerOptions().position(panama).title("Ciudad del Saber"))

        val mexico = LatLng(19.42736700000002, -99.16522090000001)
        mMap.addMarker(MarkerOptions().position(mexico).title("Torre Reforma Latino - Piso 41"))

    }

}



