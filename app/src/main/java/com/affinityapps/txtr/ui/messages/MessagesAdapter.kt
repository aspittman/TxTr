package com.affinityapps.txtr.ui.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.affinityapps.txtr.databinding.MessageListItemsBinding
import com.affinityapps.txtr.ui.home.Messages

class MessagesAdapter(
    private val messagesFragmentArrayList: List<Messages>,
) :
    RecyclerView.Adapter<MessagesAdapter.MessagesFragmentViewHolder>() {

    inner class MessagesFragmentViewHolder(binding: MessageListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var dateTextView: TextView = binding.dataDate
        var timeTextView: TextView = binding.dataTime
        var messageTextView: TextView = binding.dataMessage

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessagesAdapter.MessagesFragmentViewHolder {
        return MessagesFragmentViewHolder(
            MessageListItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MessagesAdapter.MessagesFragmentViewHolder,
        position: Int
    ) {
        val messages: Messages = messagesFragmentArrayList[position]
        holder.dateTextView.text = messages.date
        holder.timeTextView.text = messages.time
        holder.messageTextView.text = messages.message
    }

    override fun getItemCount() = messagesFragmentArrayList.size
}