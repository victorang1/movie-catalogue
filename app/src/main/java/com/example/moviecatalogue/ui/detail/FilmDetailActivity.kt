package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    private var selectedFilmId: Int = 0
    private var selectedType: String = ""

    private val addToFavoriteListener = View.OnClickListener { showAddToFavoriteDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_film_detail)
        selectedFilmId = intent.getIntExtra(FILM_ID, -1)
        selectedType = intent.getStringExtra(TYPE).toString()
        initializeObserver()
        getResult()
    }

    private fun getResult() {
        if (selectedFilmId != -1 && selectedType.isNotEmpty()) {
            mViewModel.loadDataById(selectedFilmId, selectedType)
                .observe(this, Observer { film ->
                    when (film) {
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
        }
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

        checkFavoriteFilm()
    }

    private fun checkFavoriteFilm() {
        mViewModel.isFavoriteFilm(selectedFilmId, selectedType)
            .observe(this, Observer { isFavorite ->
                when (isFavorite) {
                    is Resource.Success -> {
                        mViewModel.setLoading(false)
                        if (isFavorite.data == false) {
                            mBinding.btnFavorite.text =
                                resources.getString(R.string.add_to_favorite)
                            mBinding.btnFavorite.setOnClickListener(addToFavoriteListener)
                            mBinding.btnFavorite.isEnabled = true
                        } else {
                            mBinding.btnFavorite.isEnabled = false
                            mBinding.btnFavorite.text =
                                resources.getString(R.string.already_favorite)
                        }
                    }
                    is Resource.Error -> {
                        mViewModel.setLoading(false)
                        mBinding.btnFavorite.visibility = View.GONE
                    }
                    is Resource.Loading -> mViewModel.setLoading(true)

                }
            })
    }

    private fun showAddToFavoriteDialog() {
        val mBuilder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setTitle(getString(R.string.text_confirmation))
            .setMessage(getString(R.string.text_add_favorite_dialog))
            .setPositiveButton(getString(R.string.text_yes)) { _, _ ->
                mViewModel.addToFavorite(mBinding.film as Film)
                    .observe(this, Observer { isSuccess ->
                        when (isSuccess) {
                            is Resource.Success -> {
                                checkFavoriteFilm()
                                Toast.makeText(
                                    this,
                                    getString(R.string.text_add_to_favorite_success),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            }
            .setNegativeButton(getString(R.string.text_no)) { dialog, _ ->
                dialog.cancel()
            }
        val alertDialog = mBuilder.create()
        alertDialog.show()
    }

//    private fun showRemoveFromFavoriteDialog() {
//        val mBuilder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
//            .setTitle(getString(R.string.text_confirmation))
//            .setMessage(getString(R.string.text_remove_favorite_dialog))
//            .setPositiveButton(getString(R.string.text_yes)) { _, _ ->
//                mViewModel.removeFromFavorite(mBinding.film.id)
//            }
//            .setNegativeButton(getString(R.string.text_no)) { dialog, _ ->
//                dialog.cancel()
//            }
//        val alertDialog = mBuilder.create()
//        alertDialog.show()
//    }
}