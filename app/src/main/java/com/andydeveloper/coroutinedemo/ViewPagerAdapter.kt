package com.andydeveloper.coroutinedemo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> SearchFragment()
        1 -> ResultFragment()
        else -> throw RuntimeException("Should not be here")
    }
}
