package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityFilmDetailBinding
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmDetailActivity : AppCompatActivity() {

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
        val selectedType = intent.getIntExtra(TYPE, -1)
        if (selectedFilmId != -1 && selectedType != -1) {
            mViewModel.loadDataById(selectedFilmId, selectedType)
                .observe(this, Observer { film ->
                    renderSelectedFilm(film)
                })
        } else finish()
    }

    private fun renderSelectedFilm(film: Film) {
        mBinding.film = film
        mBinding.ivThumbnail.loadImage(film.image)
        mViewModel.setLoading(false)
    }

    private fun initializeObserver() {
        mViewModel.getLoadingStatus().observe(this, Observer { isLoading ->
            mBinding.isLoading = isLoading
        })
    }

    companion object {
        const val FILM_ID = "filmId"
        const val TYPE = "type"
    }
}