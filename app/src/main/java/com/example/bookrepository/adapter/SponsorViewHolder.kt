package com.example.bookrepository.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.bookrepository.data.Sponsor
import com.example.bookrepository.databinding.SponsorItemBinding

class SponsorViewHolder(private val binding: SponsorItemBinding) : RecyclerView.ViewHolder(binding.root) {
    // Método para enlazar los datos del sponsor con las vistas
    fun bind(sponsor: Sponsor) {
        binding.ivSponsorLogo.setImageResource(sponsor.logo)
    }
}
