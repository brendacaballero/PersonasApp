package com.example.personasapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personasapp.data.model.Persona
import com.example.personasapp.data.repository.PersonaRepository
import kotlinx.coroutines.launch

class PersonaViewModel : ViewModel() {

    private val repository = PersonaRepository()

    var personas by mutableStateOf<List<Persona>>(emptyList())
        private set

    //obtener personas
    fun obtenerPersonas() {
        viewModelScope.launch {

            try {
                personas = repository.obtenerPersonas()

            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    //Registrar persona
    fun registrarPersona(persona: Persona) {
        viewModelScope.launch {

            try {
                repository.registrarPersona(persona)
                obtenerPersonas()

            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    //Actualizar persona
    fun actualizarPersona(id: String, persona: Persona) {
        viewModelScope.launch {

            try {
                repository.actualizarPersona(id, persona)
                obtenerPersonas()

            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    //Eliminar persona
    fun eliminarPersona(id: String) {
        viewModelScope.launch {

            try {
                repository.eliminarPersona(id)
                obtenerPersonas()

            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }


}

