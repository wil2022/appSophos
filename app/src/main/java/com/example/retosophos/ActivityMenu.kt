package com.example.retosophos

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import android.Manifest.permission
import android.Manifest.permission.CAMERA
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.retosophos.model.Login
import retrofit2.Call
import retrofit2.Response

//lateinit var buttonCamara: Button
const val REQUEST_CODE = 42
private val CAMERA_REQUEST_CODE = 0
class ActivityMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var user = findViewById<TextView>(R.id.textViewUsuario)

        user.text = usuario

        val boton1 = findViewById<Button>(R.id.buttonBlue)
        boton1.setOnClickListener {
            val intent = Intent(this, EnvioDocsActivity::class.java)
            startActivity(intent)

        }

        val boton2 = findViewById<Button>(R.id.buttonGreen)
        boton2.setOnClickListener {
            val intent = Intent(this, VerDocsActivity::class.java)
            startActivity(intent)

        }
        val boton3 = findViewById<Button>(R.id.buttonMaps)
        boton3.setOnClickListener {
            val intent = Intent(this, MapsOficinasActivity::class.java)
            startActivity(intent)

        }



    }

    //------------------------END_ONCREATE--------------------------------//


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




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

}