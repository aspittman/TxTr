package com.affinityapps.txtr.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.affinityapps.txtr.R
import com.affinityapps.txtr.databinding.FragmentMessagesBinding
import com.affinityapps.txtr.databinding.FragmentSummaryBinding

class SummaryFragment() : Fragment() {

    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        //times texted today
        //times texted in a week
        //times texted in a month
        //how many words used
        //words used on average
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}