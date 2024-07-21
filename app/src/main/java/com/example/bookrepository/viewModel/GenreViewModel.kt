package com.example.bookrepository.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookrepository.data.Genre
import com.example.bookrepository.data.GenreList

class GenreViewModel : ViewModel() {
    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> = _genreList

    init {
        _genreList.value = GenreList.listOfGenres
    }

    fun addGenre(genre: Genre) {
        val updatedList = _genreList.value.orEmpty().toMutableList()
        updatedList.add(genre)
        _genreList.value = updatedList
    }

    fun removeGenre(genre: Genre) {
        val updatedList = _genreList.value.orEmpty().toMutableList()
        updatedList.remove(genre)
        _genreList.value = updatedList
    }
}
