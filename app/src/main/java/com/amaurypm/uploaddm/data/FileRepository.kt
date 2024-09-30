package com.amaurypm.uploaddm.data

import com.amaurypm.uploaddm.data.remote.RetrofitHelper
import com.amaurypm.uploaddm.data.remote.UploadApi
import okhttp3.MultipartBody
import retrofit2.http.Multipart

class FileRepository {

    private val uploadApi = RetrofitHelper.getRetrofit().create(UploadApi::class.java)

    fun uploadImage(image: MultipartBody.Part) = uploadApi.uploadImage(image)
}