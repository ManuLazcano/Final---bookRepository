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

    // Asocia los datos del libro con la vista
    fun bind(book: Book) {
        // Establece los valores de los campos en el ítem del libro
        binding.tvTitle.text = book.title
        binding.tvAuthor.text = book.author
        binding.tvGenre.text = book.genre

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

        // Rellena los campos del diálogo con la información actual del libro
        dialogBookBinding.etTitle.setText(book.title)
        dialogBookBinding.etAuthor.setText(book.author)
        dialogBookBinding.etGenre.setText(book.genre)
        dialogBookBinding.etSynopsis.setText(book.synopsis)

        // Configura el botón de añadir en el diálogo para actualizar el libro
        dialogBookBinding.btnAdd.setOnClickListener {
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

    // Muestra un diálogo para confirmar la eliminación del libro
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
