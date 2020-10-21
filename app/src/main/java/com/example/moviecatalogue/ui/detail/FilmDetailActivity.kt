package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.databinding.ActivityFilmDetailBinding
import com.example.moviecatalogue.data.local.entity.Film
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmDetailActivity : AppCompatActivity() {

    companion object {
        const val FILM_ID = "filmId"
        const val TYPE = "type"
    }

    private lateinit var mBinding: ActivityFilmDetailBinding
    private val mViewModel: FilmDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_film_detail)
        getResult()
        initializeObserver()
    }

    private fun getResult() {
        val selectedFilmId = intent.getIntExtra(FILM_ID, -1)
        val selectedType = intent.getStringExtra(TYPE)
        if (selectedFilmId != -1 && !selectedType.isNullOrEmpty()) {
            mViewModel.loadDataById(selectedFilmId, selectedType)
                .observe(this, Observer { film ->
                    when(film) {
                        is Resource.Success -> {
                            if (film.data != null) {
                                renderSelectedFilm(film.data)
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
        } else finish()
    }

    private fun renderSelectedFilm(film: Film) {
        mBinding.film = film
        Glide.with(this)
            .load(film.image)
            .apply(RequestOptions.errorOf(R.drawable.ic_error_black))
            .into(mBinding.ivThumbnail)
    }

    private fun initializeObserver() {
        mViewModel.getLoadingStatus().observe(this, Observer { isLoading ->
            mBinding.isLoading = isLoading
        })
    }
}