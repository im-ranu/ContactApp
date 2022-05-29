package com.kisannetwork.contactsapp.ui.otplist.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kisannetwork.contactsapp.R
import com.kisannetwork.contactsapp.data.models.Message
import com.kisannetwork.contactsapp.databinding.ItemViewMessageBinding
import java.text.SimpleDateFormat

class MessageAdapter(
    var otpList: ArrayList<Message>,
    var itemClickListener : (Message) -> Unit
) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {


    class ViewHolder(var binding : ItemViewMessageBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_view_message,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(otpList[position]){
                val date = formatDate(this.timestamp)
                binding.tvDateTime.text = "Sent at ${date.uppercase()}"
                binding.message = this
                binding.cvParent.setOnClickListener {
                    itemClickListener(this)
                }
                binding.executePendingBindings()

            }
        }

    }

    override fun getItemCount(): Int {
        return otpList.size
    }

    fun formatDate(timestamp: Long):String {
        var simpleDateFormat = SimpleDateFormat("dd/MM/yyyy, HH:mm:ss aa")
        return simpleDateFormat.format(timestamp)
    }
}