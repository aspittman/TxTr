package com.affinityapps.txtr.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.affinityapps.txtr.R
import com.affinityapps.txtr.ui.forum.ForumFragment

class StatisticsFragment() : Fragment() {

    private lateinit var statisticsViewModel: StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statisticsViewModel = ViewModelProvider(this).get(StatisticsViewModel::class.java)
        val root = inflater.inflate(R.layout.statistics_fragment, container, false)
        val textView:TextView = root.findViewById(R.id.statistics_test)
        statisticsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    companion object {
        fun newInstance(): StatisticsFragment = StatisticsFragment()
    }
}