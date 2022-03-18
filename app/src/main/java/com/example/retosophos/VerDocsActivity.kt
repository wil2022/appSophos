package com.example.retosophos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retosophos.model.verDocs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.retosophos.adapters.DataAdpter
import com.example.retosophos.model.Items


var dataList = ArrayList<Items>()
lateinit var recyclerView: RecyclerView
lateinit var adapter:DataAdpter
//lateinit var adjfoto: String

class VerDocsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_docs)

        recyclerView = findViewById(R.id.recycler_view)
//setting up the adapter
        recyclerView.adapter= DataAdpter(dataList,this)
        recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


        getDocuments()


        val actionBar = supportActionBar

        actionBar!!.title = "Documentos"


    }

    //-------------------END_ONCREATE--------------------------//

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


   //---------------------------------------------------------//

    private fun getDocuments() {
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getDocs(e_mail)

        retrofitData.enqueue(object : Callback<verDocs> {
            override fun onResponse(call: Call<verDocs?>, response: Response<verDocs?>) {

                dataList.addAll(response.body()!!.Items)
                recyclerView.adapter?.notifyDataSetChanged()
            /*
                var adj = response.body()!!.Items[9].Adjunto
                adjfoto = adj
                //decode base64 string to image
                var byteArray: ByteArray = Base64.decode(adj, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                foto.setImageBitmap(decodedImage)
            */
            }
            override fun onFailure(call: Call<verDocs?>, t: Throwable) {
                Log.d("VerDocsActivity", "onFailure: " + t.message)
            }
        })


    }


}