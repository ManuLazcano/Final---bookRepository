package com.example.bookrepository.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.bookrepository.data.Genre
import com.example.bookrepository.databinding.GenreItemBinding

class GenreViewHolder(private val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: Genre) {
        binding.tvGenreName.text = genre.name
    }
}
