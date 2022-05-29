package com.kisannetwork.contactsapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    var first_name: String,
    var last_name : String,
    var phone : String,
    var city : String,
    var picture : String) : Parcelable
