package com.example.retosophos

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.retosophos.model.Login
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


var BASE_URL = "https://6w33tkx4f9.execute-api.us-east-1.amazonaws.com/"
var usuario: String = ""
var e_mail: String = ""


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    companion object {
        val instance = MainActivity()
    }

    private var cancellationSignal: CancellationSignal? = null
    private val  authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object: BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    notifyUser("Authentication error: $errString")
                }
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    notifyUser("Authentication Success!")
                    startActivity(Intent(this@MainActivity, ActivityMenu::class.java))
                }
            }
    @RequiresApi(Build.VERSION_CODES.P)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

        val botonIngresar = findViewById<Button>(R.id.buttonIngresar)
        botonIngresar.setOnClickListener {

           getAcceso()

        }

        checkBiometricSupport()
        val botonHuella = findViewById<Button>(R.id.buttonHuella)
        botonHuella.setOnClickListener{
            val biometricPrompt : BiometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("Autenticacion Biometrica")
                .setSubtitle("Autenticacion es requerida")
                .setDescription("Fingerprint Authentication")
                .setNegativeButton("Cancel", this.mainExecutor, DialogInterface.OnClickListener { dialog, which ->
                }).build()
            biometricPrompt.authenticate(getCancellationSignal(), mainExecutor, authenticationCallback)
        }



    }



       private fun getAcceso() {
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val intent = Intent(this, ActivityMenu::class.java)


            //val intent2 = Intent(this, EnvioDocsActivity::class.java)
            // var usuario = findViewById<TextView>(R.id.textoNombre)

        var correo = findViewById<EditText>(R.id.editTextEmail)

        var password = findViewById<EditText>(R.id.editTextPassword)



        val retrofitData = retrofitBuilder.getLogin("${correo.text}","${password.text}")
           e_mail = correo.text.toString()


        retrofitData.enqueue(object : Callback<Login?> {
            override fun onResponse(call: Call<Login?>, response: Response<Login?>) {

                if (response.body()!!.acceso) {

                    startActivity(intent)
                    usuario = response.body()!!.nombre


                } else if (!response.body()!!.acceso) {
                    Toast.makeText(applicationContext, "credenciales invalidas", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Login?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }





    private fun notifyUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Authentication was cancelled by the user")
        }
        return cancellationSignal as CancellationSignal
    }
    private fun checkBiometricSupport(): Boolean {
        val keyguardManager : KeyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if(!keyguardManager.isKeyguardSecure) {
            notifyUser("Fingerprint hs not been enabled in settings.")
            return false
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            notifyUser("Fingerprint hs not been enabled in settings.")
            return false
        }
        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true
        } else true
    }


    }




/*
    val BASE_URL = "https://6w33tkx4f9.execute-api.us-east-1.amazonaws.com/"
    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

*/




