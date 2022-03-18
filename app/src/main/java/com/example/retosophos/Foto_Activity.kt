package com.example.retosophos

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import com.example.retosophos.adapters.dataAdj
//import com.example.retosophos.adapters.dataAdj
import com.example.retosophos.adapters.imgFoto
//import com.example.retosophos.adapters.imgFoto
import com.example.retosophos.model.verDocs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Foto_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto)


//getadjFoto()

      var fotoadj = findViewById<ImageView>(R.id.imageViewFotoAdj)

        //fotoadj.setImageBitmap(imgFoto)

        //decode base64 string to image
        var byteArray: ByteArray = Base64.decode(dataAdj, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        fotoadj.setImageBitmap(decodedImage)


    //println(dataAdj)


    }
/*
    private fun getadjFoto() {
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getFoto(dataAdj)

        var fotoadj = findViewById<ImageView>(R.id.imageViewFotoAdj)

        retrofitData.enqueue(object : Callback<verDocs> {
            override fun onResponse(call: Call<verDocs?>, response: Response<verDocs?>) {



                if(response.isSuccessful){


                            var adjfoto = response.body()!!.Items[0].Adjunto
                            //decode base64 string to image
                            var byteArray: ByteArray = Base64.decode(adjfoto, Base64.DEFAULT)
                            val decodedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                            fotoadj.setImageBitmap(decodedImage)
                            println(dataAdj)
                }



            }
            override fun onFailure(call: Call<verDocs?>, t: Throwable) {
                Log.d("Foto_Activity", "onFailure: " + t.message)
            }
        })


    }

*/


}