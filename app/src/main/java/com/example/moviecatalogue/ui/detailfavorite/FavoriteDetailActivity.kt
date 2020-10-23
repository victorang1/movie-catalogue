package com.example.moviecatalogue.ui.detailfavorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.databinding.ActivityFavoriteDetailBinding
import org.koin.android.ext.android.inject

class FavoriteDetailActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityFavoriteDetailBinding
    private val mViewModel: FavoriteDetailViewModel by inject()

    private var selectedFilmId: Int = 0
    private var selectedType: String = ""

    companion object {
        const val SELECTED_ID = "selected_id"
        const val SELECTED_TYPE = "selected_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_favorite_detail)
        selectedFilmId = intent.getIntExtra(SELECTED_ID, -1)
        selectedType = intent.getStringExtra(SELECTED_TYPE).toString()
        initializeObserver()
        getResult()
    }

    private fun getResult() {
        if (selectedFilmId != -1 && selectedType.isNotEmpty()) {
            mViewModel.loadDataById(selectedFilmId, selectedType)
                .observe(this, Observer { favorite ->
                    when (favorite) {
                        is Resource.Success -> {
                            if (favorite.data != null) {
                                renderSelectedFavorite(favorite.data)
                            } else {
                                mBinding.tvError.visibility = View.VISIBLE
                            }
                            mViewModel.setLoading(false)
                        }
                        is Resource.Error -> {
                            mViewModel.setLoading(false)
                            mBinding.tvError.visibility = View.VISIBLE
                        }
                        is Resource.Loading -> {
                            mViewModel.setLoading(true)
                        }
                    }
                })
        }
    }

    private fun initializeObserver() {
        mViewModel.getLoadingStatus().observe(this, Observer { isLoading ->
            mBinding.isLoading = isLoading
        })
    }

    private fun renderSelectedFavorite(favorite: Favorite) {
        mBinding.favorite = favorite
        Glide.with(this)
            .load(favorite.image)
            .apply(RequestOptions.errorOf(R.drawable.ic_error_black))
            .into(mBinding.ivThumbnail)
    }
}