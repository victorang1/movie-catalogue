package com.example.moviecatalogue.ui.favorite

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.constant.AppConstant
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding
import org.koin.android.ext.android.inject

class FavoriteActivity : AppCompatActivity(), FavoriteItemClickCallback,
    SearchView.OnQueryTextListener {

    private lateinit var mBinding: ActivityFavoriteBinding
    private lateinit var mAdapter: FavoriteAdapter
    private val mViewModel: FavoriteViewModel by inject()
    private lateinit var searchView: SearchView

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
        resetShowedData()

        mViewModel.getLoadingStatus().observe(this, Observer { isLoading ->
            mBinding.isLoading = isLoading
        })
    }

    private fun resetShowedData() {
        mViewModel.getShowedData().observe(this, Observer { resource ->
            if (resource != null) {
                when (resource) {
                    is Resource.Success -> {
                        if (!resource.data.isNullOrEmpty()) {
                            setError(false)
                            mAdapter.submitList(resource.data)
                            mAdapter.notifyDataSetChanged()
                        } else {
                            setError(true)
                            mBinding.tvMessage.text = getString(R.string.text_no_data)
                        }
                        mViewModel.setLoading(false)
                    }
                    is Resource.Loading -> mViewModel.setLoading(true)
                    is Resource.Error -> {
                        setError(true)
                        mViewModel.setLoading(false)
                    }
                }
            }
        })
    }

    private fun setError(isError: Boolean) {
        mBinding.rvFavorite.visibility = if(isError) View.GONE else View.VISIBLE
        mBinding.tvMessage.visibility = if(isError) View.VISIBLE else View.GONE
    }

    private fun initializeSearchView(menu: Menu) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.menu_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.text_search_hint)
        searchView.setOnQueryTextListener(this)
        searchView.clearFocus()
    }

    override fun onRemoveClick(favorite: Favorite) {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        initializeSearchView(menu)
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            mViewModel.searchUser(query).observe(this, Observer { resource ->
                if (resource != null) {
                    when (resource) {
                        is Resource.Success -> {
                            if (!resource.data.isNullOrEmpty()) {
                                setError(false)
                                mAdapter.submitList(resource.data)
                                mAdapter.notifyDataSetChanged()
                            } else {
                                setError(true)
                                mBinding.tvMessage.text = getString(R.string.text_no_data)
                            }
                            mViewModel.setLoading(false)
                        }
                        is Resource.Loading -> mViewModel.setLoading(true)
                        is Resource.Error -> {
                            setError(true)
                            mViewModel.setLoading(false)
                        }
                    }
                }
                searchView.clearFocus()
            })
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        resetShowedData()
        return true
    }

}