package com.example.bookrepository

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookrepository.databinding.ActivityBookDetailsBinding

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el título y la sinopsis del libro del intent, obtiene un extra de tipo String del intent que lanzó esta actividad
        val title = intent.getStringExtra("BOOK_TITLE") ?: ""
        val synopsis = intent.getStringExtra("BOOK_SYNOPSIS") ?: ""

        binding.tvBookTitle.text = title
        binding.tvBookSynopsis.text = synopsis

        binding.btnBackToMain.setOnClickListener {

            finish()
        }
    }
}