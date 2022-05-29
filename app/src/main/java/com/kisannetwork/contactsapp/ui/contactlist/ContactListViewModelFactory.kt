package com.kisannetwork.contactsapp.ui.contactlist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kisannetwork.contactsapp.ui.home.ContactListViewModel
import com.kisannetwork.contactsapp.ui.home.OTPListViewModel


class ContactListViewModelFactory(private val mContext: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactListViewModel(mContext) as T
    }
}
