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

class SummaryFragment() : Fragment() {

    private lateinit var summaryViewModel: SummaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        summaryViewModel = ViewModelProvider(this).get(SummaryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_forum, container, false)
        val textView:TextView = root.findViewById(R.id.forum_test)
        summaryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}