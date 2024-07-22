package com.example.bookrepository.data

data class Sponsor(
    //En Android los recursos como las imagenes se gestionan mediante identificadores enteros.
    // Al definir el logo como un Int, podemos hacer referencia a estos recursos de manera eficiente y evitar errores relacionados con la gestión manual de rutas de archivo o URLs.
    val logo: Int
)
