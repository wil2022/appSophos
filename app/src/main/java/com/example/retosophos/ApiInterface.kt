package com.example.retosophos


import com.example.retosophos.model.EnvioDocs
import com.example.retosophos.model.verDocs
import com.example.retosophos.model.Login
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body





interface ApiInterface {

    @GET("RS_Usuarios")
    fun getLogin(@Query("idUsuario") email: String,
                 @Query("clave") password: String): Call<Login>

    @POST("RS_Documentos")
    open fun EnvDocsPost(@Body post: EnvioDocs): Call<EnvioDocs>


    @GET("RS_Documentos")
    fun getDocs(@Query("correo") correo: String): Call<verDocs>


    @GET("RS_Documentos")
    fun getFoto(@Query("idRegistro") idReg: String): Call<verDocs>



    /*
    {
  "TipoId": "string",
  "Identificacion": "string",
  "Nombre": "string",
  "Apellido": "string",
  "Ciudad": "string",
  "Correo": "string",
  "TipoAdjunto": "string",
  "Adjunto": "string"
}
     */
}








