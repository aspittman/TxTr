package com.affinityapps.txtr.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.affinityapps.txtr.databinding.DataListItemsBinding

class HomeAdapter(private val homeFragmentArrayList: List<Contact>) :
    RecyclerView.Adapter<HomeAdapter.HomeFragmentViewHolder>() {

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


    class HomeFragmentViewHolder(binding: DataListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var nameTextView: TextView = binding.dataName
        var dateTextView: TextView = binding.dataDate
      //  var timeTextView: TextView = binding.dataTime
    }
}