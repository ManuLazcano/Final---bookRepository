package com.example.bookrepository

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookrepository.databinding.ActivityContactFormBinding

class ContactFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val message = binding.etMessage.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && message.isNotEmpty()) {
                // Aquí puedes manejar el envío del formulario, por ejemplo, enviarlo a un servidor o guardarlo localmente
                Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show()
                clearForm()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            finish() // Volver a la actividad anterior
        }
    }

    private fun clearForm() {
        binding.etName.text.clear()
        binding.etEmail.text.clear()
        binding.etMessage.text.clear()
    }
}
