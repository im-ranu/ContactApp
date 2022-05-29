package com.kisannetwork.contactsapp.ui.contactlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kisannetwork.contactsapp.R
import com.kisannetwork.contactsapp.data.models.Contact
import com.kisannetwork.contactsapp.databinding.ItemViewContactBinding

class ContactAdapter(
    var contactList: ArrayList<Contact>,
    var itemClickListener : (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {


    class ViewHolder(var binding : ItemViewContactBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_view_contact,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(contactList[position]){

                binding.contactAvatar.load(this.picture)
                binding.contact = this
                binding.cvParent.setOnClickListener {
                    itemClickListener(this)
                }
                binding.executePendingBindings()

            }
        }

    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}