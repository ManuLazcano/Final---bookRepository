package com.example.bookrepository

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookrepository.adapter.SponsorListAdapter
import com.example.bookrepository.data.SponsorList
import com.example.bookrepository.databinding.ActivityContactFormBinding

class ContactFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar los bindings para la vista del formulario de contacto
        binding = ActivityContactFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el botón de envío del formulario
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
            finish()
        }

        initRecycler()
    }

    private fun clearForm() {
        binding.etName.text.clear()
        binding.etEmail.text.clear()
        binding.etMessage.text.clear()
    }

    private fun initRecycler() {
        binding.rvSponsors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSponsors.adapter = SponsorListAdapter(SponsorList.listOfSponsors)
    }
}
