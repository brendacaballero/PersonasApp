package com.example.personasapp.data.repository

import com.example.personasapp.data.model.Persona
import com.example.personasapp.data.network.RetrofitInstance

class PersonaRepository {
    private val api = RetrofitInstance.api

    suspend fun obtenerPersonas(): List<Persona> {
        return api.obtenerPersonas()
    }

    suspend fun registrarPersona(persona: Persona): Persona {
        return api.registrarPersona(persona)
    }

    suspend fun actualizarPersona(id: String, persona: Persona): Persona {
        return api.actualizarPersona(id, persona)
    }

    suspend fun eliminarPersona(id: String) {
        api.eliminarPersona(id)
    }
}