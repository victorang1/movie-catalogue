package com.example.moviecatalogue.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityMainBinding
import com.example.moviecatalogue.ui.favorite.FavoriteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val homePagerAdapter = HomePagerAdapter(this, supportFragmentManager)
        mBinding.viewPager.adapter = homePagerAdapter
        mBinding.tabs.setupWithViewPager(mBinding.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
            }
        }
        return true
    }
}