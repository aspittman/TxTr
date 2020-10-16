package com.affinityapps.txtr.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.affinityapps.txtr.databinding.DataListItemsBinding

class HomeAdapter(
    private val homeFragmentArrayList: List<Contact>,
    private val listener: OnHomeItemClickListener) :
    RecyclerView.Adapter<HomeAdapter.HomeFragmentViewHolder>() {

    interface OnHomeItemClickListener {
        fun onHomeItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder {
        return HomeFragmentViewHolder(
            DataListItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {

        val contact: Contact = homeFragmentArrayList[position]
        holder.nameTextView.text = contact.name
        holder.dateTextView.text = contact.number
    }

    override fun getItemCount() = homeFragmentArrayList.size


    inner class HomeFragmentViewHolder(binding: DataListItemsBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var nameTextView: TextView = binding.dataName
        var dateTextView: TextView = binding.dataDate

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onHomeItemClick(position)
            }
        }
    }
}