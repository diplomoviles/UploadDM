package com.amaurypm.uploaddm.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object{

        fun getRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://www.serverbpw.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }
}