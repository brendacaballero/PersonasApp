package com.example.personasapp.data.network

import com.example.personasapp.data.model.Persona
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface PersonaApi {

    @GET("person")
    suspend fun obtenerPersonas(): List<Persona>

    @POST("person")
    suspend fun registrarPersona(
        @Body persona: Persona
    ): Persona

    @PUT("person/{id}")
    suspend fun actualizarPersona(
        @Path("id") id: String,
        @Body persona: Persona
    ): Persona

    @DELETE("person/{id}")
    suspend fun eliminarPersona(
        @Path("id") id: String
    )
}