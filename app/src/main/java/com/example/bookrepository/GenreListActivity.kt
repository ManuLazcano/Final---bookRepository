package com.example.bookrepository

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookrepository.adapter.GenreListAdapter
import com.example.bookrepository.data.Genre
import com.example.bookrepository.databinding.ActivityGenreListBinding
import com.example.bookrepository.databinding.DialogGenreBinding
import com.example.bookrepository.viewModel.GenreViewModel

class GenreListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGenreListBinding
    private lateinit var dialogGenreBinding: DialogGenreBinding
    private lateinit var genreViewModel: GenreViewModel
    private lateinit var genreListAdapter: GenreListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGenreListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        genreViewModel = GenreViewModel()
        genreListAdapter = GenreListAdapter(listOf())
        initRecycler()

        genreViewModel.genreList.observe(this, Observer { genreList ->
            genreListAdapter.updateGenreList(genreList)
        })

        binding.btnAddGenre.setOnClickListener {
            showAddGenreDialog()
        }
    }

    private fun initRecycler() {
        binding.rvGenres.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvGenres.adapter = genreListAdapter
    }

    private fun showAddGenreDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogGenreBinding = DialogGenreBinding.inflate(layoutInflater)
        dialogBuilder.setView(dialogGenreBinding.root)
        val alertDialog = dialogBuilder.create()

        dialogGenreBinding.fabAddGenre.setOnClickListener {
            val newGenreName = dialogGenreBinding.etGenreName.text.toString()
            if (newGenreName.isNotEmpty()) {
                genreViewModel.addGenre(Genre(newGenreName))
                alertDialog.dismiss()
            }
        }

        alertDialog.show()
    }
}

