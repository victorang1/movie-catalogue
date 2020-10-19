package com.example.moviecatalogue.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FilmItemLayoutBinding
import com.example.moviecatalogue.model.Film

class FilmAdapter(val mCallback: FilmClickCallback, private val filmList: ArrayList<Film>) :
    RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemBinding =
            FilmItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val filmData = filmList[position]
        holder.bind(filmData)
    }

    override fun getItemCount(): Int = filmList.count()

    fun setDataSet(filmList: List<Film>) {
        this.filmList.clear()
        this.filmList.addAll(filmList)
        notifyDataSetChanged()
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