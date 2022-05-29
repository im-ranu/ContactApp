package com.kisannetwork.contactsapp.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kisannetwork.contactsapp.data.Repository
import com.kisannetwork.contactsapp.data.models.Contact
import com.kisannetwork.contactsapp.data.models.Message
import com.kisannetwork.contactsapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OTPListViewModel(repository: Repository) : BaseViewModel() {


    var otpList = MutableLiveData<ArrayList<Message>>()

    var repository : Repository



    init {
        this.repository  = repository
    }


    fun getOtpList():LiveData<ArrayList<Message>>{

        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getOtpList()

            withContext(Dispatchers.Main){
                otpList.value = list
            }

        }
        return otpList

    }



}