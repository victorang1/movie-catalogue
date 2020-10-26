package com.example.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.constant.AppConstant
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.ui.FilmAdapter
import com.example.moviecatalogue.ui.FilmClickCallback
import com.example.moviecatalogue.ui.detail.FilmDetailActivity
import com.example.moviecatalogue.ui.detail.FilmDetailActivity.Companion.FILM_ID
import com.example.moviecatalogue.ui.detail.FilmDetailActivity.Companion.TYPE
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class TvShowFragment : Fragment(), FilmClickCallback {

    companion object {
        @JvmStatic
        fun newInstance() = TvShowFragment()
    }

    private lateinit var mBinding: FragmentTvShowBinding
    private lateinit var mAdapter: FilmAdapter
    private val mViewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentTvShowBinding.inflate(inflater, container, false)
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
            putExtra(TYPE, AppConstant.TV_SHOW)
        }
        startActivity(intent)
    }

    private fun initializeAdapter() {
        mAdapter = FilmAdapter(this)
        with(mBinding.rvShows) {
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
        try {
            mViewModel.getTvShowData().observe(viewLifecycleOwner, Observer { tvShows ->
                if (tvShows != null) {
                    when (tvShows) {
                        is Resource.Success -> {
                            mViewModel.setLoading(false)
                            if (!tvShows.data.isNullOrEmpty()) {
                                mAdapter.submitList(tvShows.data)
                                mAdapter.notifyDataSetChanged()
                                mBinding.tvMessage.visibility = View.GONE
                            } else {
                                mBinding.tvMessage.visibility = View.VISIBLE
                                mBinding.tvMessage.text = resources.getString(R.string.text_no_data)
                            }
                        }
                        is Resource.Loading -> mViewModel.setLoading(true)
                        is Resource.Error -> {
                            mViewModel.setLoading(false)
                            Toast.makeText(requireContext(), tvShows.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })
        } catch (e: Exception) {
            mBinding.tvMessage.visibility = View.VISIBLE
            mBinding.tvMessage.text = e.message
        }
    }
}