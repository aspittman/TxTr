package com.affinityapps.txtr.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.affinityapps.txtr.databinding.DataListItemsBinding

private lateinit var homeFragmentArrayList: ArrayList<Contact>
private lateinit var binding: DataListItemsBinding

class HomeAdapter(private val homeFragmentArrayList: ArrayList<Contact>) :
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
        holder.dateTextView.text = contact.date
        holder.timeTextView.text = contact.time.toString()
    }

    override fun getItemCount() = homeFragmentArrayList.size


    class HomeFragmentViewHolder(binding: DataListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var nameTextView: TextView = binding.dataName
        var dateTextView: TextView = binding.dataDate
        var timeTextView: TextView = binding.dataTime
    }
}