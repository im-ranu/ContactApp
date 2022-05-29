package com.kisannetwork.contactsapp.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface NexmoApi {

    @FormUrlEncoded
    @POST("sms/json")
     fun sendMessage(
    @FieldMap metadata : Map<String,String>
    ): Call<ResponseBody>
}