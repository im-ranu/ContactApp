package com.kisannetwork.contactsapp.ui.contactlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisannetwork.contactsapp.data.Repository
import com.kisannetwork.contactsapp.data.models.Message
import com.kisannetwork.contactsapp.databinding.FragmentOtpListBinding
import com.kisannetwork.contactsapp.ui.home.OTPListViewModel
import com.kisannetwork.contactsapp.ui.otplist.adapter.MessageAdapter

class OTPListFragment : Fragment() {


    var mBinding : FragmentOtpListBinding? =null
    var otpList = ArrayList<Message>()
    var adapter : MessageAdapter? = null
    var repository:Repository? = null
    private val otpListViewModel: OTPListViewModel
    by viewModels { OTPListViewModelFactory(repository ?: Repository(this.requireContext())) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentOtpListBinding.inflate(inflater,container,false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        getData()

    }

    private fun init() {
        mBinding?.rvMain?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        adapter = MessageAdapter(otpList){

        }
        mBinding?.rvMain?.adapter = adapter
    }

    private fun getData() {
        otpListViewModel.getOtpList().observe(viewLifecycleOwner) {
            if (it.size>0){
                mBinding?.noDataFound?.visibility = View.GONE
                otpList.addAll(it)
                adapter?.notifyDataSetChanged()
            }else{
                mBinding?.noDataFound?.visibility = View.VISIBLE
            }

        }

    }
}