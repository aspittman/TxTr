package com.affinityapps.txtr.ui.graphs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.affinityapps.txtr.R
import com.affinityapps.txtr.databinding.FragmentStatisticsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class StatisticsFragment() : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    private val tabNames =
        intArrayOf(R.string.tab_day, R.string.tab_week, R.string.tab_month)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager: ViewPager2 = binding.pager
        val tabLayout: TabLayout = binding.tabs
        viewPager.adapter = createCardAdapter()
        TabLayoutMediator(
            tabLayout, viewPager
        ) { tab, position -> tab.setText(tabNames[position]) }.attach()
    }

    private fun createCardAdapter(): StatisticsPagerAdapter? {
        return StatisticsPagerAdapter(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}