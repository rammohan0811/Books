package com.learning.headspace.repository.networking

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class  Repository {
    companion object Factory {
        private var gson = GsonBuilder().setLenient().create()
        private  const val BASE_URL = "https://api.nytimes.com"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()
            return  retrofit.create(ApiInterface::class.java)
        }
    }
}