package com.kisannetwork.contactsapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey(autoGenerate = true) var id : Int,
    @ColumnInfo(name="phone") var phone : String,
    @ColumnInfo(name="message") var message: String,
    @ColumnInfo(name="timestamp") var timestamp : Long)
