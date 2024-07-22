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

    // Crea una nueva instancia de ViewHolder y la vincula con la vista del ítem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding, bookViewModel)
    }

    // Crea una nueva instancia de ViewHolder y la vincula con la vista del ítem
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.bind(book)

        // Configura el clic en el ítem para mostrar los detalles del libro
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            // Crea un Intent para iniciar BookDetailsActivity
            val intent = Intent(context, BookDetailsActivity::class.java).apply {
                putExtra("BOOK_TITLE", book.title) // Pasa el título del libro
                putExtra("BOOK_SYNOPSIS", book.synopsis) // Pasa la sinopsis del libro
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = bookList.size

    fun updateBookList(newBookList: List<Book>) {
        bookList = newBookList
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
    }
}

