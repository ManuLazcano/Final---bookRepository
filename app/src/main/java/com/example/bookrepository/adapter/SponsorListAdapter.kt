package com.example.bookrepository.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookrepository.data.Sponsor
import com.example.bookrepository.databinding.SponsorItemBinding
import com.example.bookrepository.viewholder.SponsorViewHolder

class SponsorListAdapter(private var sponsorList: List<Sponsor>) : RecyclerView.Adapter<SponsorViewHolder>() {

    // Crea una nueva vista para un ítem del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorViewHolder {
        // Infla el layout del item usando el binding
        val binding = SponsorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // Devuelve una nueva instancia de SponsorViewHolder con el binding inflado
        return SponsorViewHolder(binding)
    }

    // Asocia los datos del sponsor con la vista del ViewHolder
    override fun onBindViewHolder(holder: SponsorViewHolder, position: Int) {
        holder.bind(sponsorList[position])
    }

    override fun getItemCount(): Int = sponsorList.size
}
