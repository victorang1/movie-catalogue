package com.example.moviecatalogue.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.databinding.FavoriteItemLayoutBinding

class FavoriteAdapter(val mCallback: FavoriteItemClickCallback) :
    PagedListAdapter<Film, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemBinding =
            FavoriteItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val filmData = getItem(position) as Favorite
        holder.bind(filmData)
    }

    inner class FavoriteViewHolder(private val itemBinding: FavoriteItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(favoriteData: Favorite) {
            with(itemBinding) {
                favorite = favoriteData
                cvFilm.setOnClickListener { mCallback.onRemoveClick(favoriteData) }
                Glide.with(root)
                    .load(favoriteData.image)
                    .apply(RequestOptions.errorOf(R.drawable.ic_error_white))
                    .into(ivThumbnail)
            }
        }
    }
}