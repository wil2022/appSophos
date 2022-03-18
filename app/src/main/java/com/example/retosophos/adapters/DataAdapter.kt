package com.example.retosophos.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
//import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.retosophos.ActivityMenu
import com.example.retosophos.Foto_Activity
import com.example.retosophos.R
import com.example.retosophos.dataList
import com.example.retosophos.model.Items
import com.example.retosophos.model.verDocs

lateinit var dataAdj:String
lateinit var imgFoto: Bitmap

class DataAdpter(private var dataList: List<Items>, private val context: Context) : RecyclerView.Adapter<DataAdpter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_home, parent, false))

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataList.get(position)


            holder.titleTextView.text = (dataModel.Fecha+" - "+dataModel.TipoAdjunto+"\n"+dataModel.Nombre +" "+ dataModel.Apellido)
            dataAdj = dataModel.IdRegistro
            holder.render(dataModel)

    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var titleTextView:TextView = itemLayoutView.findViewById(R.id.buttonDoc)
        //val botonimg = itemLayoutView.findViewById<Button>(R.id.buttonDoc)

        fun render(Items: Items){
/*
            botonimg.setOnClickListener {


                val intent = Intent(botonimg.context, Foto_Activity::class.java)
                botonimg.context.startActivity(intent)
                }

*/

            itemView.setOnClickListener{
/*
                var byteArray: ByteArray = Base64.decode(adjphoto, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                imgFoto = decodedImage
                //println(dataAdj)
*/
                val intent = Intent(itemView.context, Foto_Activity::class.java)
                itemView.context.startActivity(intent)
            }


        }


    }

}
