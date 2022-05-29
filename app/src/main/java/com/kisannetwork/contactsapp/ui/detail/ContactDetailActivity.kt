package com.kisannetwork.contactsapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import coil.load
import com.kisannetwork.contactsapp.R
import com.kisannetwork.contactsapp.data.models.Contact
import com.kisannetwork.contactsapp.databinding.ActivityContactDetailsBinding
import com.kisannetwork.contactsapp.databinding.CommonToolbarBinding
import com.kisannetwork.contactsapp.extentions.Constants
import com.kisannetwork.contactsapp.ui.base.BaseActivity
import com.kisannetwork.contactsapp.ui.sendotp.SendOTPActivity

class ContactDetailActivity : BaseActivity(),View.OnClickListener {


    var mBinding : ActivityContactDetailsBinding? = null
    var mToolbarBinding : CommonToolbarBinding? = null
    var contactItem : Contact? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)

        getIntentData()
        init()
        toolbarInit()
    }

    private fun toolbarInit() {

        setSupportActionBar(mToolbarBinding?.toolbar)
        supportActionBar?.setTitle("${contactItem?.first_name}'s Details")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun getIntentData() {
        contactItem = intent?.getParcelableExtra(Constants.INTENT_CONTACT)
    }

    fun init(){
        mToolbarBinding = mBinding?.commonToolbarContactDetails
        mBinding?.contact = contactItem

        mBinding?.contactAvatar?.load(contactItem?.picture)
        mBinding?.btSendOtp?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.bt_send_otp->{
                openSendOTPActivity()
            }
        }
    }

    private fun openSendOTPActivity() {
        val intent = Intent(this,SendOTPActivity::class.java)
        intent.putExtra(Constants.INTENT_CONTACT,contactItem)
        startActivity(intent)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return false
    }
}