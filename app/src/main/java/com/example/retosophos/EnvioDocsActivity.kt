package com.example.retosophos

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.retosophos.model.EnvioDocs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.text.method.ScrollingMovementMethod
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import androidx.core.graphics.drawable.toDrawable
import java.io.ByteArrayOutputStream
import java.io.IOException


private const val CAMERA_REQUEST_CODE = 1
private const val REQUEST_SELECT_IMAGE_IN_ALBUM = 2


class EnvioDocsActivity : AppCompatActivity() {
    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envio_docs)





        val botonCamara = findViewById<Button>(R.id.buttonCamara)
        botonCamara.setOnClickListener {

            checkCameraPermission()

        }

        val botonAdjuntar = findViewById<Button>(R.id.buttonAdjuntar)

        botonAdjuntar.setOnClickListener{
            selectImageInAlbum()

        }

      val botonEnviar = findViewById<Button>(R.id.buttonEnviar)

        botonEnviar.setOnClickListener{
            EnviarDocs()

        }


        //-------------------------------------------------------------//

        val spinnerTipoDoc: Spinner = findViewById(R.id.spinnerTipodoc)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.tipo_doc,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerTipoDoc.adapter = adapter
        }


        var tipoId = findViewById<TextView>(R.id.textViewTipoId)
        spinnerTipoDoc.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view

                tipoId.text = "${parent.getItemAtPosition(position)}"

            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        //--------------------------------------------------------------------//


        val spinnerCiudad: Spinner = findViewById(R.id.spinnerCiudad)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.ciudad,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerCiudad.adapter = adapter
        }

        var ciudad = findViewById<TextView>(R.id.textViewCiudad)
        spinnerCiudad.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view

                ciudad.text = "${parent.getItemAtPosition(position)}"

            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

   //----------------------------------------------------------------------------//

        val spinnerTipoAdjunto: Spinner = findViewById(R.id.spinnerTipoAdjunto)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.tipo_adjunto,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerTipoAdjunto.adapter = adapter
        }

        var adjunto = findViewById<TextView>(R.id.textViewAdjunto)
        spinnerTipoAdjunto.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view

                adjunto.text = "${parent.getItemAtPosition(position)}"

            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        //---------------------------------------------------------//

        var foto = findViewById<ImageView>(R.id.imagePhoto)

/*
        //encode image to base64 string
        val baos = ByteArrayOutputStream()
        val bitmap = BitmapFactory.decodeResource(resources, R.id.imagePhoto)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        var imageBytes: ByteArray = baos.toByteArray()
        val imageString: String = Base64.encodeToString(imageBytes, Base64.DEFAULT)


        //decode base64 string to image
        imageBytes = Base64.decode(imageString, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        image.setImageBitmap(decodedImage)

*/
var textBase64 = findViewById<TextView>(R.id.textViewBase64)

/*
// make text view text scrollable
        textBase64.movementMethod = ScrollingMovementMethod()

        // get the bitmap from assets folder
        val bitmap = BitmapFactory.decodeResource(resources, R.id.imagePhoto)

        bitmap?.apply {
            // show bitmap in image view
            foto.setImageBitmap(this)

            //show base64 string of bitmap
            textBase64.text = toBase64String()
        }


*/


        val correo = findViewById<TextView>(R.id.textViewCorreo)

         correo.text = e_mail

/*
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.banner)
        if (bitmap != null) {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            var byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            val fotoEnBase64: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
            textBase64.text = fotoEnBase64

            //decode base64 string to image
            byteArray = Base64.decode(fotoEnBase64, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            foto.setImageBitmap(decodedImage)

        }

*/

    }

    //----------------------END_ONCREATE--------------------------------//






    // method to inflate the options menu when
    // the user opens the menu for the first time
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



    fun EnviarDocs() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val NumId = findViewById<EditText>(R.id.editTextNumber)
        val nombre = findViewById<EditText>(R.id.editTextNombre)
        val apellido = findViewById<EditText>(R.id.editTextApellido)


        var tipoId = findViewById<TextView>(R.id.textViewTipoId)
        var ciudad = findViewById<TextView>(R.id.textViewCiudad)
        var adjunto = findViewById<TextView>(R.id.textViewAdjunto)
        var textBase64 = findViewById<TextView>(R.id.textViewBase64)



        val retrofitData = retrofitBuilder.EnvDocsPost(EnvioDocs("${tipoId.text}", "${NumId.text}",
                                                                 "${nombre.text}","${apellido.text}",
                                                                 "${ciudad.text}",e_mail,
                                                                 "${adjunto.text}","${textBase64.text}"))


            retrofitData.enqueue(object : Callback<EnvioDocs?> {
                override fun onResponse(call: Call<EnvioDocs?>, response: Response<EnvioDocs?>) {


                    if (response.isSuccessful) {


                        Toast.makeText(applicationContext, "Envio exitoso", Toast.LENGTH_SHORT)
                            .show()

                    }
                }

                override fun onFailure(call: Call<EnvioDocs?>, t: Throwable) {
                    Log.d("EnvioDocsActivity", "onFailure: " + t.message)
                }
            })
        }




    fun selectImageInAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
        startActivityForResult(intent,REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }



    fun abrirCamara() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var foto = findViewById<ImageView>(R.id.imagePhoto)
        var textBase64 = findViewById<TextView>(R.id.textViewBase64)
        when(requestCode){
            CAMERA_REQUEST_CODE->{
                if(resultCode != Activity.RESULT_OK){
                    Toast.makeText(this, "no se tomo foto", Toast.LENGTH_SHORT).show()

                }else {

                    val bitmap = data?.extras?.get("data") as Bitmap

                   // foto.setImageBitmap(bitmap)



                        val byteArrayOutputStream = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                        var byteArray: ByteArray = byteArrayOutputStream.toByteArray()
                        val fotoEnBase64: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
                        textBase64.text = fotoEnBase64

                        //decode base64 string to image
                        byteArray = Base64.decode(fotoEnBase64, Base64.DEFAULT)
                        val decodedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                        foto.setImageBitmap(decodedImage)



                }

            }


        }

        when(requestCode){
            REQUEST_SELECT_IMAGE_IN_ALBUM->{
                if(resultCode != Activity.RESULT_OK){
                    Toast.makeText(this, "no selecciono foto", Toast.LENGTH_SHORT).show()

                }else {


                    //val imagen = data?.extras?.get("data") as Bitmap
                    val imagen = data?.data

                    //foto.setImageURI(imagen)

                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imagen)

                    val imageScaled = Bitmap.createScaledBitmap(bitmap, 300, 300, false)

                        val byteArrayOutputStream = ByteArrayOutputStream()
                        imageScaled.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                        var byteArray: ByteArray = byteArrayOutputStream.toByteArray()
                        val fotoEnBase64: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
                        textBase64.text = fotoEnBase64

                        //decode base64 string to image
                        byteArray = Base64.decode(fotoEnBase64, Base64.DEFAULT)
                        val decodedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                        foto.setImageBitmap(decodedImage)

                }

            }
        }


    }


    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            //El permiso no está aceptado.
            requestCameraPermission()
        } else {
            //El permiso está aceptado.
            abrirCamara()

        }
    }

    fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            )
        ) {
            //El usuario ya ha rechazado el permiso anteriormente, debemos informarle que vaya a ajustes.
            Toast.makeText(applicationContext, "vaya a ajustes para habilitar el permiso", Toast.LENGTH_SHORT).show()
        } else {
            //El usuario nunca ha aceptado ni rechazado, así que le pedimos que acepte el permiso.

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    abrirCamara()//El usuario ha aceptado el permiso, no tiene porqué darle de nuevo al botón, podemos lanzar la funcionalidad desde aquí.
                } else {
                    //El usuario ha rechazado el permiso, podemos desactivar la funcionalidad o mostrar una vista/diálogo.
                }
                return
            }
            else -> {
                // Este else lo dejamos por si sale un permiso que no teníamos controlado.
            }
        }
    }


}