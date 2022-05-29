package com.kisannetwork.contactsapp.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kisannetwork.contactsapp.data.models.Contact
import com.kisannetwork.contactsapp.data.models.Message
import com.kisannetwork.contactsapp.database.AppDatabase
import com.kisannetwork.contactsapp.extentions.Utils
import org.json.JSONObject

class Repository(private val context: Context) {


    fun getContactList(fileName : String):ArrayList<Contact> {
        val jsonFromAssets = Utils.loadJSONFromAssets(context, fileName)

        val jsonObject = JSONObject(jsonFromAssets)
        val string = jsonObject.getJSONArray("data").toString()
        val type = object : TypeToken<ArrayList<Contact>>() {}.type
        val contactList = Gson().fromJson<ArrayList<Contact>>(string, type)
        return contactList
    }

    fun getOtpList(): ArrayList<Message> {
        val instance = AppDatabase.getInstance(context)
        val all = instance.messageDao().getAll()
        return ArrayList(all)
    }

}