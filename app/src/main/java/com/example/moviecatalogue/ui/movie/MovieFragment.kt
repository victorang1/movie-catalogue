package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.ui.FilmAdapter
import com.example.moviecatalogue.ui.FilmClickCallback
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        loadMovieData()
    }

    override fun onItemClick(film: Film) {
        TODO("Not yet implemented")
    }

    private fun initializeAdapter() {
        mAdapter = FilmAdapter(this, arrayListOf())
        with(mBinding.recyclerView) {
            this.layoutManager = LinearLayoutManager(activity)
            this.adapter = mAdapter
        }
    }

    private fun loadMovieData() {
        mViewModel.getMovieData().observe(requireActivity(), Observer { movies ->
            mAdapter.setDataSet(movies)
        })
    }
}