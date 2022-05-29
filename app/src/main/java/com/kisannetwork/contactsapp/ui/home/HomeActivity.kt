package com.kisannetwork.contactsapp.ui.home

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kisannetwork.contactsapp.R
import com.kisannetwork.contactsapp.databinding.ActivityHomeBinding
import com.kisannetwork.contactsapp.databinding.ToolbarBinding
import com.kisannetwork.contactsapp.ui.base.BaseActivity
import com.kisannetwork.contactsapp.ui.home.adapter.ViewPagerAdapter

class HomeActivity : BaseActivity() {


    var mBinding : ActivityHomeBinding? = null
    var mToolbarBinding : ToolbarBinding? = null
    val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        //use splash api (beta version)
        installSplashScreen()
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)
        init()
    }


    private fun init(){
        mToolbarBinding = mBinding?.include
        mBinding?.viewPager?.setAdapter(ViewPagerAdapter(this))
        TabLayoutMediator(mToolbarBinding?.tabLayout!!, mBinding?.viewPager!!,object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                if (position==0){
                    tab.setText(R.string.first_tab_text)
                }else tab.setText(R.string.second_tab_text)
            }

        }).attach()
    }
}