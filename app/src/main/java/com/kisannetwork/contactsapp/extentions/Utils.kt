package com.kisannetwork.contactsapp.extentions

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.kisannetwork.contactsapp.data.models.Contact
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class Utils {



    companion object{
        fun loadJSONFromAssets(context : Context,name : String): String? {
            var json: String? = null
            json = try {
                val `is`: InputStream = context.assets.open(name)
                val size: Int = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, Charset.forName("UTF-8"))
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }
    }


}
