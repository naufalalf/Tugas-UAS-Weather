package com.mi17.myweather

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class SlidingAdapter (fm: FragmentManager): FragmentPagerAdapter(fm){

    // sebuah list yang menampung objek Fragment
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitle = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position]
    }

    fun addFrag(fragment: Fragment) {
        fragmentList.add(fragment)
        fragmentTitle.add("")
    }

    fun addFrag(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitle.add(title)
    }
}