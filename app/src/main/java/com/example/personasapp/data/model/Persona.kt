package com.example.personasapp.data.model

data class Persona(
    var createdAt: String = "",
    var nombre: String = "",
    var pais: String = "",
    var direccion: String = "",
    var estadocivil: Boolean = false,
    var trabajo: String = "",
    var id: String = "",
)