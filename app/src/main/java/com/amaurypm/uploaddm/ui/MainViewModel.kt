package com.amaurypm.uploaddm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaurypm.uploaddm.data.FileRepository
import com.amaurypm.uploaddm.data.remote.model.UploadResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MainViewModel: ViewModel() {

    private val repository: FileRepository = FileRepository()

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun uploadImage(file: File){
        viewModelScope.launch(Dispatchers.IO) {
            //Vamos transformando el file para que sea aceptado por la función uploadImage

            val requestBody = file.asRequestBody()



            val imagePart = MultipartBody.Part.createFormData(
                "image",  //nombre del parámetro que espera mi backend
                file.name,
                requestBody
            )

            val call: Call<UploadResponseDto> = repository.uploadImage(imagePart)

            call.enqueue(object: Callback<UploadResponseDto>{
                override fun onResponse(
                    p0: Call<UploadResponseDto>,
                    response: Response<UploadResponseDto>
                ) {
                    _message.postValue(response.body()?.message)
                }

                override fun onFailure(p0: Call<UploadResponseDto>, p1: Throwable) {
                    //Manejo del error
                }

            })

        }
    }

}