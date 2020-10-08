package com.affinityapps.txtr.ui.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val TAB_ITEM_SIZE = 3;
private const val DAY = "hoursPosition";
private const val WEEK = "milesPosition";
private const val MONTH = "purchasesPosition";

class StatisticsPagerAdapter(fragmentActivity : FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {

        val fragment:Fragment
        val args:Bundle

        when (position) {
            0 -> {
            fragment = DayGraphFragment()
            args = Bundle()
            args.putInt(DAY, position + 1)
            fragment.setArguments(args)
            return fragment }
            1 -> {
            fragment = WeekGraphFragment()
            args = Bundle()
            args.putInt(WEEK, position + 1);
            fragment.setArguments(args);
            return fragment; }
            else -> {
            fragment = MonthGraphFragment()
            args = Bundle()
            args.putInt(MONTH, position + 1);
            fragment.setArguments(args);
            return fragment }
        }
    }

    override fun getItemCount(): Int {
        return TAB_ITEM_SIZE
    }
}