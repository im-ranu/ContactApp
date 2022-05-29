package com.kisannetwork.contactsapp.ui.contactlist

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisannetwork.contactsapp.data.Repository
import com.kisannetwork.contactsapp.data.models.Contact
import com.kisannetwork.contactsapp.databinding.FragmentContactlistBinding
import com.kisannetwork.contactsapp.extentions.Constants
import com.kisannetwork.contactsapp.ui.contactlist.adapter.ContactAdapter
import com.kisannetwork.contactsapp.ui.detail.ContactDetailActivity
import com.kisannetwork.contactsapp.ui.home.ContactListViewModel


class ContactListFragment : Fragment() {


    var mBinding : FragmentContactlistBinding? =null
    var contactList = ArrayList<Contact>()
    var adapter : ContactAdapter? = null
    var layoutManager : LinearLayoutManager? = null
    var repository : Repository? =null
    private val contactListViewModel: ContactListViewModel
    by viewModels { ContactListViewModelFactory(repository ?: Repository(this.requireContext())) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentContactlistBinding.inflate(inflater,container,false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        getData()

    }

    private fun init() {
        layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)


        mBinding?.rvMain?.layoutManager =layoutManager
            adapter = ContactAdapter(contactList){
            val intent = Intent(requireActivity(),ContactDetailActivity::class.java)
            intent.putExtra(Constants.INTENT_CONTACT,it)
            startActivity(intent)
        }
        mBinding?.rvMain?.adapter = adapter
    }

    private fun getData() {
        //this json is custom made by me. It is having 7000 items in JSONArray
        contactListViewModel.fileName = "contacts.json"
        contactListViewModel.getContactList().observe(viewLifecycleOwner, Observer {
            contactList.addAll(it)
        })

    }



}