package com.example.moviecatalogue.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity(), FavoriteItemClickCallback{

    private lateinit var mBinding: ActivityFavoriteBinding
    private lateinit var mAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_favorite)
        initializeAdapter()
    }

    private fun initializeAdapter() {
        mAdapter = FavoriteAdapter(this)
        mBinding.rvFavorite.layoutManager = LinearLayoutManager(this)
        mBinding.rvFavorite.adapter = mAdapter
    }

    override fun onRemoveClick(favorite: Favorite) {
        TODO("Not yet implemented")
    }
}