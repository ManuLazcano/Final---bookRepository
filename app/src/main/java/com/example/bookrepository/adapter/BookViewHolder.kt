package com.example.bookrepository.viewholder

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.bookrepository.data.Book
import com.example.bookrepository.databinding.BookItemBinding
import com.example.bookrepository.databinding.DialogBookBinding
import com.example.bookrepository.viewModel.BookViewModel

class BookViewHolder(
    private val binding: BookItemBinding,
    private val bookViewModel: BookViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book) {
        binding.tvTitle.text = book.title
        binding.tvAuthor.text = book.author
        binding.tvGenre.text = book.genre
        //binding.tvSynopsis.text = book.synopsis

        binding.btnEdit.setOnClickListener {
            showEditDialog(book)
        }

        binding.btnDelete.setOnClickListener {
            showDeleteDialog(book)
        }
    }

    private fun showEditDialog(book: Book) {
        val dialogBuilder = AlertDialog.Builder(binding.root.context)
        val dialogBookBinding = DialogBookBinding.inflate(LayoutInflater.from(binding.root.context))
        dialogBuilder.setView(dialogBookBinding.root)
        val alertDialog = dialogBuilder.create()

        dialogBookBinding.etTitle.setText(book.title)
        dialogBookBinding.etAuthor.setText(book.author)
        dialogBookBinding.etGenre.setText(book.genre)
        dialogBookBinding.etSynopsis.setText(book.synopsis)

        dialogBookBinding.fabPlus.setOnClickListener {
            val updatedTitle = dialogBookBinding.etTitle.text.toString()
            val updatedAuthor = dialogBookBinding.etAuthor.text.toString()
            val updatedGenre = dialogBookBinding.etGenre.text.toString()
            val updatedSynopsis = dialogBookBinding.etSynopsis.text.toString()

            if (updatedTitle.isNotEmpty() && updatedAuthor.isNotEmpty() && updatedGenre.isNotEmpty() && updatedSynopsis.isNotEmpty()) {
                bookViewModel.updateBook(book, updatedTitle, updatedAuthor, updatedGenre, updatedSynopsis)
                alertDialog.dismiss()
            }
        }

        alertDialog.show()
    }

    private fun showDeleteDialog(book: Book) {
        val dialogBuilder = AlertDialog.Builder(binding.root.context)
        dialogBuilder.setTitle("Confirmar eliminación")
        dialogBuilder.setMessage("¿Esta seguro que quiere eliminar este libro?")
        dialogBuilder.setPositiveButton("Si") { dialog, _ ->
            bookViewModel.removeBook(book)
            dialog.dismiss()
        }
        dialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        dialogBuilder.create().show()
    }
}
