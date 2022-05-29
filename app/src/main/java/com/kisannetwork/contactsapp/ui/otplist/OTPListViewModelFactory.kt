package com.kisannetwork.contactsapp.ui.contactlist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kisannetwork.contactsapp.ui.home.OTPListViewModel


class OTPListViewModelFactory(private val mContext: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OTPListViewModel(mContext) as T
    }
}
