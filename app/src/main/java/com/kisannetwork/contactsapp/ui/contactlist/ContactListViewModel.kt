package com.kisannetwork.contactsapp.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kisannetwork.contactsapp.data.Repository
import com.kisannetwork.contactsapp.data.models.Contact
import com.kisannetwork.contactsapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactListViewModel(repository: Repository) : BaseViewModel() {


    var contactList = MutableLiveData<ArrayList<Contact>>()

    var repository : Repository

    var fileName = ""
        set(value) {
            field = value
        }

    init {
        this.repository  = repository
    }


    fun getContactList():LiveData<ArrayList<Contact>>{

        viewModelScope.launch {
            val list = repository.getContactList(fileName)

            withContext(Dispatchers.Main){
                contactList.value = list
            }

        }
        return contactList

    }



}