package com.affinityapps.txtr.ui.home

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.affinityapps.txtr.databinding.DataListItemsBinding

private const val TAG = "HomeAdapter"

class HomeAdapter(
    private val homeFragmentArrayList: List<Contacts>,
) :
    RecyclerView.Adapter<HomeAdapter.HomeFragmentViewHolder>() {
    private lateinit var listener: OnHomeItemClickListener
    private var rowIndex: Int = -1

    interface OnHomeItemClickListener {
        fun onHomeItemClick(position: Int)
    }

    fun setOnHomeItemClickListener(listener: OnHomeItemClickListener) {
        this.listener = listener
    }

    inner class HomeFragmentViewHolder(binding: DataListItemsBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var contactsLinearLayout: CardView = binding.contactsCardView
        var nameTextView: TextView = binding.dataName
        var numberTextView: TextView = binding.dataNumber

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder {
        return HomeFragmentViewHolder(
            DataListItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {

        val contacts: Contacts = homeFragmentArrayList[position]
        holder.nameTextView.text = contacts.name
        holder.numberTextView.text = contacts.number

//        holder.contactsLinearLayout.setOnClickListener {
//            rowIndex = position
//            notifyDataSetChanged()
//            Log.d(TAG, "color on")
//        }
//
//        if (rowIndex == position) {
//            holder.contactsLinearLayout.setBackgroundColor(Color.parseColor("#03DAC5"))
//            holder.nameTextView.setTextColor(Color.parseColor("#ffffff"))
//            holder.numberTextView.setTextColor(Color.parseColor("#ffffff"))
//        } else {
//            holder.contactsLinearLayout.setBackgroundColor(Color.parseColor("#6200EE"))
//            holder.nameTextView.setTextColor(Color.parseColor("#ffffff"))
//            holder.numberTextView.setTextColor(Color.parseColor("#ffffff"))
//        }
    }

    override fun getItemCount() = homeFragmentArrayList.size
}