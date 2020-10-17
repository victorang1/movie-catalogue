package com.example.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.ui.FilmAdapter
import com.example.moviecatalogue.ui.FilmClickCallback
import com.example.moviecatalogue.ui.detail.FilmDetailActivity
import com.example.moviecatalogue.ui.detail.FilmDetailActivity.Companion.FILM_ID
import com.example.moviecatalogue.ui.detail.FilmDetailActivity.Companion.TYPE
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        mAdapter.setDataSet(mViewModel.getTvShowData())
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(requireContext(), FilmDetailActivity::class.java).apply {
            putExtra(FILM_ID, position)
            putExtra(TYPE, R.string.text_type_tv_show)
        }
        startActivity(intent)
    }

    private fun initializeAdapter() {
        mAdapter = FilmAdapter(this, arrayListOf())
        with(mBinding.recyclerView) {
            this.layoutManager = LinearLayoutManager(activity)
            this.adapter = mAdapter
        }
    }
}