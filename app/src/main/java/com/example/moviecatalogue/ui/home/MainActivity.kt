package com.example.moviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val homePagerAdapter = HomePagerAdapter(this, supportFragmentManager)
        mBinding.viewPager.adapter = homePagerAdapter
        mBinding.tabs.setupWithViewPager(mBinding.viewPager)
    }
}