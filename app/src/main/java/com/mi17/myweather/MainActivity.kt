 package com.mi17.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

 class MainActivity : AppCompatActivity() {

     private var tabLayout: TabLayout? = null
     private var viewPager: ViewPager? = null
     private var slidingAdapter: SlidingAdapter? = null


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         tabLayout = findViewById(R.id.tabs_main)
         viewPager = findViewById(R.id.viewpager_main)

         slidingAdapter = SlidingAdapter(supportFragmentManager)
         tabLayout!!.setupWithViewPager(viewPager)

         slidingAdapter!!.addFrag(KotaFragment(), "Kota")
         slidingAdapter!!.addFrag(PilihKotaFragment(), "Pilih Kota")
         viewPager!!.adapter = slidingAdapter
     }

 }

