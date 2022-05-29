package com.kisannetwork.contactsapp.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kisannetwork.contactsapp.ui.contactlist.ContactListFragment
import com.kisannetwork.contactsapp.ui.contactlist.OTPListFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            ContactListFragment()
        } else {
            OTPListFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}