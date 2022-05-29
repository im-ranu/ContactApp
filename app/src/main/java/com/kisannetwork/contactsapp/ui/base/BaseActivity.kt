package com.kisannetwork.contactsapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.kisannetwork.contactsapp.R
import com.kisannetwork.contactsapp.data.models.Message
import com.kisannetwork.contactsapp.database.AppDatabase

open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)



    }


}