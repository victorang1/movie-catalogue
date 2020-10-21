package com.example.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.ui.FilmAdapter
import com.example.moviecatalogue.ui.FilmClickCallback
import com.example.moviecatalogue.ui.detail.FilmDetailActivity
import com.example.moviecatalogue.ui.detail.FilmDetailActivity.Companion.FILM_ID
import com.example.moviecatalogue.ui.detail.FilmDetailActivity.Companion.TYPE
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class MovieFragment : Fragment(), FilmClickCallback {

    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }

    private lateinit var mBinding: FragmentMovieBinding
    private lateinit var mAdapter: FilmAdapter
    private val mViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapter()
        initializeObserver()
        loadData()
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(requireContext(), FilmDetailActivity::class.java).apply {
            putExtra(FILM_ID, id)
            putExtra(TYPE, R.string.text_type_movie)
        }
        startActivity(intent)
    }

    private fun initializeAdapter() {
        mAdapter = FilmAdapter(this, arrayListOf())
        with(mBinding.rvMovies) {
            this.layoutManager = LinearLayoutManager(activity)
            this.adapter = mAdapter
        }
    }

    private fun initializeObserver() {
        mViewModel.getLoadingStatus().observe(viewLifecycleOwner, Observer { isLoading ->
            mBinding.isLoading = isLoading
        })
    }

    private fun loadData() {
        mViewModel.setLoading(true)
        try {
            mViewModel.getMovieData().observe(viewLifecycleOwner, Observer { movies ->
                mViewModel.setLoading(false)
//                if (movies.isNotEmpty()) {
//                    mAdapter.setDataSet(movies)
//                    mBinding.tvMessage.visibility = View.GONE
//                }
//                else {
//                    mBinding.tvMessage.visibility = View.VISIBLE
//                    mBinding.tvMessage.text = resources.getString(R.string.text_no_data)
//                }
            })
        } catch (e: Exception) {
            mViewModel.setLoading(false)
            mBinding.tvMessage.visibility = View.VISIBLE
            mBinding.tvMessage.text = e.message
        }
    }
}