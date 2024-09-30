package com.amaurypm.uploaddm.data.remote

import com.amaurypm.uploaddm.data.remote.model.UploadResponseDto
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Streaming

interface UploadApi {

    //https://www.serverbpw.com/cm/games/upload.php

    @Multipart //Para que se pueda enviar por partes el contenido
    @Streaming //Para que el contenido no se cargue completo en la memoria RAM
    @POST("cm/games/upload.php")
    fun uploadImage(
        @Part image: MultipartBody.Part //Le indicamos a retrofit que es parte de un request multipart
    ): Call<UploadResponseDto>

}