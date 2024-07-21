package com.example.bookrepository.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookrepository.BookDetailsActivity
import com.example.bookrepository.data.Book
import com.example.bookrepository.databinding.BookItemBinding
import com.example.bookrepository.viewModel.BookViewModel
import com.example.bookrepository.viewholder.BookViewHolder

class BookListAdapter(
    private var bookList: List<Book>,
    private val bookViewModel: BookViewModel
) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding, bookViewModel)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.bind(book)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BookDetailsActivity::class.java).apply {
                putExtra("BOOK_TITLE", book.title)
                putExtra("BOOK_SYNOPSIS", book.synopsis)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = bookList.size

    fun updateBookList(newBookList: List<Book>) {
        bookList = newBookList
        notifyDataSetChanged()
    }
}

