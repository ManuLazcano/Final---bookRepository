package com.example.bookrepository.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookrepository.data.Genre
import com.example.bookrepository.databinding.GenreItemBinding
import com.example.bookrepository.viewholder.GenreViewHolder

class GenreListAdapter(private var genreList: List<Genre>) : RecyclerView.Adapter<GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genreList[position])
    }

    override fun getItemCount(): Int = genreList.size

    fun updateGenreList(newGenreList: List<Genre>) {
        genreList = newGenreList
        notifyDataSetChanged()
    }
}
