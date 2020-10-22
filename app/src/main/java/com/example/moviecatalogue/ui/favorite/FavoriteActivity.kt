package com.example.moviecatalogue.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.constant.AppConstant
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding
import org.koin.android.ext.android.inject

class FavoriteActivity : AppCompatActivity(), FavoriteItemClickCallback{

    private lateinit var mBinding: ActivityFavoriteBinding
    private lateinit var mAdapter: FavoriteAdapter
    private val mViewModel: FavoriteViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_favorite)
        initializeAdapter()
        initializeObserver()
        mViewModel.setSelectedFilmType(AppConstant.MOVIE)
    }

    private fun initializeAdapter() {
        mAdapter = FavoriteAdapter(this)
        mBinding.rvFavorite.layoutManager = LinearLayoutManager(this)
        mBinding.rvFavorite.adapter = mAdapter
    }

    private fun initializeObserver() {
        mViewModel.getShowedData().observe(this, Observer { resource ->
            if (resource != null) {
                when (resource) {
                    is Resource.Success -> {
                        if (!resource.data.isNullOrEmpty()) {
                            mAdapter.submitList(resource.data)
                            mAdapter.notifyDataSetChanged()
                        } else {
                            mBinding.tvMessage.visibility = View.VISIBLE
                            mBinding.tvMessage.text = getString(R.string.text_no_data)
                        }
                        mViewModel.setLoading(false)
                    }
                    is Resource.Loading -> mViewModel.setLoading(true)
                    is Resource.Error -> {
                        mViewModel.setLoading(false)
                        mBinding.tvMessage.visibility = View.VISIBLE
                    }
                }
            }
        })

        mViewModel.getLoadingStatus().observe(this, Observer { isLoading ->
            mBinding.isLoading = isLoading
        })
    }

    override fun onRemoveClick(favorite: Favorite) {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_movie -> {
                mViewModel.setLoading(true)
                mViewModel.setSelectedFilmType(AppConstant.MOVIE)
            }
            R.id.menu_tv_shows -> {
                mViewModel.setLoading(true)
                mViewModel.setSelectedFilmType(AppConstant.TV_SHOW)
            }
        }
        return true
    }
}