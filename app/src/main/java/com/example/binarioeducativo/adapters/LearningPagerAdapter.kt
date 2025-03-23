package com.example.binarioeducativo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.binarioeducativo.fragments.BinaryInfoFragment
import com.example.binarioeducativo.fragments.ConversionFragment

class LearningPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BinaryInfoFragment()
            1 -> ConversionFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}