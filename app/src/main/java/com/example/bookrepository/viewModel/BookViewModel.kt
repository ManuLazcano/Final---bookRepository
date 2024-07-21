package com.example.bookrepository.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookrepository.data.Book
import com.example.bookrepository.data.BookList

class BookViewModel : ViewModel() {
    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    init {
        _bookList.value = BookList.listOfBooks
    }

    fun addBook(title: String, author: String, genre: String, synopsis: String) {
        val book = Book(title, author, genre, synopsis)
        val updatedList = _bookList.value.orEmpty().toMutableList()
        updatedList.add(book)
        _bookList.value = updatedList
    }

    fun removeBook(book: Book) {
        val updatedList = _bookList.value.orEmpty().toMutableList()
        updatedList.remove(book)
        _bookList.value = updatedList
    }

    fun updateBook(oldBook: Book, title: String, author: String, genre: String, synopsis: String) {
        val updatedList = _bookList.value.orEmpty().toMutableList()
        val index = updatedList.indexOf(oldBook)
        if (index != -1) {
            updatedList[index] = Book(title, author, genre, synopsis)
            _bookList.value = updatedList
        }
    }
}
