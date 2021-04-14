package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityFilmDetailBinding
import com.example.moviecatalogue.model.Film
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmDetailActivity : AppCompatActivity() {

    companion object {
        const val FILM_ID = "filmId"
        const val TYPE = "type"
        val EXCELLENT_RATING = 70..100
        val GOOD_RATING = 50..70
    }

    private lateinit var mBinding: ActivityFilmDetailBinding
    private val mViewModel: FilmDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_film_detail)
        getResult()
    }

    private fun getResult() {
        val selectedFilmId = intent.getIntExtra(FILM_ID, -1)
        val selectedType = intent.getIntExtra(TYPE, -1)
        if (selectedFilmId != -1 && selectedType != -1) {
            renderSelectedFilm(mViewModel.loadDataById(selectedFilmId, selectedType))
        } else finish()
    }

    private fun renderSelectedFilm(film: Film) {
        mBinding.film = film
        Glide.with(this)
            .load(film.image)
            .apply(RequestOptions.errorOf(R.drawable.ic_error_black))
            .into(mBinding.ivThumbnail)
        with(mBinding.score) {
            setProgressWithAnimation(film.score, 5)
            finishedStrokeColor = ContextCompat.getColor(
                this@FilmDetailActivity, when (film.score) {
                    in EXCELLENT_RATING -> R.color.colorProgressGreen
                    in GOOD_RATING -> R.color.colorProgressYellow
                    else -> R.color.colorProgressRed
                }
            )
        }
    }
}