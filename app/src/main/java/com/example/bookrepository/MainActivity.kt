package com.example.bookrepository

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookrepository.adapter.BookListAdapter
import com.example.bookrepository.data.Book
import com.example.bookrepository.databinding.ActivityMainBinding
import com.example.bookrepository.databinding.DialogBookBinding
import com.example.bookrepository.viewModel.BookViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBookBinding: DialogBookBinding
    private lateinit var bookViewModel: BookViewModel
    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar los bindings para la vista principal y el diálogo
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialogBookBinding = DialogBookBinding.inflate(layoutInflater)

        // Crear y configurar el AlertDialog para añadir un libro
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogBookBinding.root)
        val alertDialog = alertDialogBuilder.create()
        setContentView(binding.root)

        // Inicializar el ViewModel y el adaptador
        bookViewModel = BookViewModel()
        bookListAdapter = BookListAdapter(listOf(), bookViewModel)

        initRecycler()

        // Observar los cambios en la lista de libros del ViewModel
        bookViewModel.bookList.observe(this, Observer { bookList ->
            bookListAdapter.updateBookList(bookList)
        })

        setListener(alertDialog)

        // Navegar al formulario de contacto
        binding.btnOpenContactForm.setOnClickListener {
            val intent = Intent(this, ContactFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecycler() {
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        binding.rvBooks.adapter = bookListAdapter
    }

    private fun setListener(alertDialog: AlertDialog) {
        binding.fabAddBook.setOnClickListener {
            showAlertDialog(alertDialog)
        }
    }

    // Mostrar el diálogo para añadir un libro y manejar el evento de añadir
    private fun showAlertDialog(alertDialog: AlertDialog) {
        alertDialog.show()
        clearEditText()

        dialogBookBinding.btnAdd.setOnClickListener {
            val title = addTitle()
            val author = addAuthor()
            val genre = addGenre()
            val synopsis = addSynopsis()
            if (title.isNotEmpty() && author.isNotEmpty() && genre.isNotEmpty() && synopsis.isNotEmpty()) {
                bookViewModel.addBook(title, author, genre, synopsis)
                alertDialog.dismiss()
            }
        }
    }

    private fun addTitle(): String {
        return dialogBookBinding.etTitle.text.toString()
    }

    private fun addAuthor(): String {
        return dialogBookBinding.etAuthor.text.toString()
    }

    private fun addGenre(): String {
        return dialogBookBinding.etGenre.text.toString()
    }

    private fun addSynopsis(): String {
        return dialogBookBinding.etSynopsis.text.toString()
    }

    private fun clearEditText() {
        dialogBookBinding.etTitle.text.clear()
        dialogBookBinding.etAuthor.text.clear()
        dialogBookBinding.etGenre.text.clear()
        dialogBookBinding.etSynopsis.text.clear()
    }
}
