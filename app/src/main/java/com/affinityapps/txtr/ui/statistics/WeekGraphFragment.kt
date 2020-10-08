package com.affinityapps.txtr.ui.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.affinityapps.txtr.databinding.FragmentWeekGraphBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class WeekGraphFragment() : Fragment() {

    private var _binding: FragmentWeekGraphBinding? = null
    private val binding get() = _binding!!

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeekGraphBinding.inflate(inflater, container, false)
        val root = binding.root
        val barChart: BarChart = binding.weekBarChart
        val inputGraph: ArrayList<BarEntry> = ArrayList()
        inputGraph.add(BarEntry(2000f, 565f))
        inputGraph.add(BarEntry(2001f, 934f))
        inputGraph.add(BarEntry(2002f, 443f))
        inputGraph.add(BarEntry(2003f, 354f))
        inputGraph.add(BarEntry(2004f, 635f))
        inputGraph.add(BarEntry(2005f, 234f))
        inputGraph.add(BarEntry(2006f, 367f))

        val barDataSet = BarDataSet(inputGraph, "testData")
        barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f

        val barData = BarData(barDataSet)
        barChart.setFitBars(true)
        barChart.data = barData
        barChart.description.text = "Week Graph :D"
        barChart.animateY(2000)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}