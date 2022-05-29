package com.kisannetwork.contactsapp.ui.sendotp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.kisannetwork.contactsapp.R
import com.kisannetwork.contactsapp.data.models.Contact
import com.kisannetwork.contactsapp.data.models.Message
import com.kisannetwork.contactsapp.database.AppDatabase
import com.kisannetwork.contactsapp.databinding.ActivitySendOtpBinding
import com.kisannetwork.contactsapp.databinding.CommonToolbarBinding
import com.kisannetwork.contactsapp.extentions.Constants
import com.kisannetwork.contactsapp.network.NexmoApi
import com.kisannetwork.contactsapp.network.RestClient
import com.kisannetwork.contactsapp.ui.base.BaseActivity
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class SendOTPActivity : BaseActivity(),View.OnClickListener {


    var mBinding : ActivitySendOtpBinding? =null
    var mToolbarBinding : CommonToolbarBinding? = null
    var contactItem : Contact? = null
    var otp : String = ""
    var message : String= ""
    val data: MutableMap<String, String> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySendOtpBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)

        getIntentData()
        init()
        toolbarInit()
        generateOTP()
        setData()
    }

    private fun setData() {
        mBinding?.etOtpMessage?.setText(getString(R.string.otp_message,otp))
    }

    private fun toolbarInit() {

        setSupportActionBar(mToolbarBinding?.toolbar)
        supportActionBar?.setTitle("Send OTP")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getIntentData() {
        contactItem = intent?.getParcelableExtra(Constants.INTENT_CONTACT)
    }

    fun init(){
        mToolbarBinding = mBinding?.commonToolbarSendOtp
        mBinding?.btSendOtp?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.bt_send_otp->{
                sendMessage(otp)
            }
        }
    }

    private fun generateOTP() {
        val rnd = Random()
        val number: Int = rnd.nextInt(999999)

        // this will convert any number sequence into 6 character.

        // this will convert any number sequence into 6 character.
        otp = String.format("%06d", number)

    }


    private fun sendMessage(otp: String) {

         message = getString(R.string.otp_message,otp)

        data["from"] = Constants.SMS_ID
        data["text"] = message
        data["to"] = "${contactItem?.phone}"
        data["api_key"] = getString(R.string.API_KEY)
        data["api_secret"] = getString(R.string.API_SECRET)

        val apiService: NexmoApi = RestClient.getRetrofit().create(NexmoApi::class.java)

        val sendMessage = apiService.sendMessage(data)
        sendMessage.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){

                    val string = response.body()?.string()
                    val jsonObject = JSONObject(string.toString())
                    val jsonArray = jsonObject.getJSONArray("messages")
                    val nestedObject = JSONObject(jsonArray.get(0).toString())

                    try {
                        if (nestedObject.get("error-text").toString().isNotBlank()){
                            Toast.makeText(this@SendOTPActivity,nestedObject.get("error-text").toString(),Toast.LENGTH_LONG).show()
                            return
                        }

                    }catch (e:Exception){

                    }

                    try {
                        if (nestedObject.get("message-id").toString().isNotBlank()){
                            lifecycleScope.launch(Dispatchers.IO){
                                saveIntoDatabase(message)
                                withContext(Dispatchers.Main){
                                    Toast.makeText(this@SendOTPActivity,getString(R.string.successful_msg),Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }catch (e:Exception){

                    }





                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@SendOTPActivity,getString(R.string.error_msg),Toast.LENGTH_LONG).show()

            }

        })


    }

    private fun saveIntoDatabase(message: String) {

        val database: AppDatabase = AppDatabase.getInstance(application)
        var messageItem = Message(0,contactItem?.phone!!, message =message ,System.currentTimeMillis())
        database.messageDao().insertMessage(messageItem)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return false
    }
}