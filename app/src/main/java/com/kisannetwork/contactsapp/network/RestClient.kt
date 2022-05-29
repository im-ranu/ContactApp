package com.kisannetwork.contactsapp.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {


    var BASE_URL = "https://rest.nexmo.com/"


     fun getRetrofit(): Retrofit {

         val interceptor = HttpLoggingInterceptor()
         interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
         val client = OkHttpClient.Builder().addInterceptor(interceptor)
             .readTimeout(60, TimeUnit.SECONDS)
             .connectTimeout(60, TimeUnit.SECONDS).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

}