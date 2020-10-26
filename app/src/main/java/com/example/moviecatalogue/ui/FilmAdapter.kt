package com.example.moviecatalogue.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FilmItemLayoutBinding
import com.example.moviecatalogue.data.local.entity.Film

class FilmAdapter(val mCallback: FilmClickCallback) :
    PagedListAdapter<Film, FilmAdapter.FilmViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemBinding =
            FilmItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val filmData = getItem(position) as Film
        holder.bind(filmData)
    }

    inner class FilmViewHolder(private val itemBinding: FilmItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(filmData: Film) {
            with(itemBinding) {
                film = filmData
                cvFilm.setOnClickListener { mCallback.onItemClick(filmData.id) }
                Glide.with(root)
                    .load(filmData.image)
                    .apply(RequestOptions.errorOf(R.drawable.ic_error_white))
                    .into(ivThumbnail)
            }
        }
    }
}