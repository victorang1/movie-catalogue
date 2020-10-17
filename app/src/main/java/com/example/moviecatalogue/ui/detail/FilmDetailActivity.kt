package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
            mViewModel.loadDataById(selectedFilmId, selectedType).observe(this, Observer { film ->
                renderSelectedFilm(film)
            })
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
                    in 70..100 -> R.color.colorProgressGreen
                    in 50..70 -> R.color.colorProgressYellow
                    else -> R.color.colorProgressRed
                }
            )
        }
    }
}