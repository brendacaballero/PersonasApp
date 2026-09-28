package com.example.personasapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.personasapp.data.model.Persona
import com.example.personasapp.viewmodel.PersonaViewModel

@Composable
fun PantallaPersonas(
    viewModel: PersonaViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var estadoCivil by remember { mutableStateOf(false) }
    var trabajo by remember { mutableStateOf("") }

    var menuPaisesAbierto by remember { mutableStateOf(false) }

    var mensajeError by remember { mutableStateOf("") }

    val paises = listOf(
        "Bolivia",
        "Argentina",
        "Brasil",
        "Chile",
        "Perú"
    )

    var personaAEliminar by remember {
        mutableStateOf<Persona?>(null)
    }

    var personaAEditar by remember {
        mutableStateOf<Persona?>(null)
    }

    var nombreEditar by remember { mutableStateOf("") }
    var paisEditar by remember { mutableStateOf("") }
    var direccionEditar by remember { mutableStateOf("") }
    var estadoCivilEditar by remember { mutableStateOf(false) }
    var trabajoEditar by remember { mutableStateOf("") }

    var menuPaisesEditarAbierto by remember {
        mutableStateOf(false)
    }

    var mensajeErrorEditar by remember {
        mutableStateOf("")
    }


    LaunchedEffect(Unit) {
        viewModel.obtenerPersonas()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp),

        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 24.dp
        )
    ) {
        //Encabezado
        item {
            Text(
                text = "App Personas",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Registra y administra personas",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        //Formulario
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Nueva persona",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    // Nombre
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = {
                            Text("Nombre")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Pais
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        OutlinedButton(
                            onClick = {
                                menuPaisesAbierto = true
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text = if (pais.isEmpty()) {
                                    "Seleccione un país"
                                } else {
                                    pais
                                }
                            )
                        }

                        DropdownMenu(
                            expanded = menuPaisesAbierto,
                            onDismissRequest = {
                                menuPaisesAbierto = false
                            }
                        ) {

                            paises.forEach { opcion ->

                                DropdownMenuItem(
                                    text = {
                                        Text(opcion)
                                    },
                                    onClick = {

                                        pais = opcion

                                        menuPaisesAbierto = false
                                    }
                                )
                            }
                        }
                    }
                    // Direccion
                    OutlinedTextField(
                        value = direccion,
                        onValueChange = {
                            direccion = it
                        },
                        label = {
                            Text("Dirección")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Estado civil
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column {

                            Text(
                                text = "Estado civil",
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = if (estadoCivil) "Casado/a" else "Soltero/a",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Switch(
                            checked = estadoCivil,
                            onCheckedChange = {
                                estadoCivil = it
                            }
                        )
                    }

                    // Trabajo
                    OutlinedTextField(
                        value = trabajo,
                        onValueChange = {
                            trabajo = it
                        },
                        label = {
                            Text("Trabajo")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Mensaje de error
                    if (mensajeError.isNotEmpty()) {

                        Text(
                            text = mensajeError,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    // Boton para registrar
                    Button(
                        onClick = {
                            if (nombre.isBlank()) {
                                mensajeError = "El nombre no puede estar vacío"

                            } else if (pais.isBlank()) {
                                mensajeError = "Debe seleccionar un país"

                            } else if (direccion.isBlank()) {
                                mensajeError = "La dirección no puede estar vacía"

                            } else if (trabajo.isBlank()) {
                                mensajeError = "El trabajo no puede estar vacío"

                            } else {
                                mensajeError = ""

                                val nuevaPersona = Persona(
                                    nombre = nombre,
                                    pais = pais,
                                    direccion = direccion,
                                    estadocivil = estadoCivil,
                                    trabajo = trabajo
                                )

                                viewModel.registrarPersona(nuevaPersona)

                                // Limpiar formulario
                                nombre = ""
                                pais = ""
                                direccion = ""
                                estadoCivil = false
                                trabajo = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth()

                    ) {
                        Text("REGISTRAR")
                    }
                }
            }

        }

        // Personas registradas

        item {

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HorizontalDivider()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Personas registradas:",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "${viewModel.personas.size} registros",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }


        items(viewModel.personas) { persona ->

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = persona.nombre,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    HorizontalDivider()

                    Text(
                        text = "🌎  ${persona.pais}"
                    )

                    Text(
                        text = "📍  ${persona.direccion}"
                    )

                    Text(
                        text = if (persona.estadocivil) {
                            "💍  Casado/a"
                        } else {
                            "👤  Soltero/a"
                        }
                    )

                    Text(
                        text = "💼  ${persona.trabajo}"
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        OutlinedButton(
                            onClick = {

                                personaAEditar = persona

                                nombreEditar = persona.nombre
                                paisEditar = persona.pais
                                direccionEditar = persona.direccion
                                estadoCivilEditar = persona.estadocivil
                                trabajoEditar = persona.trabajo

                                mensajeErrorEditar = ""
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                        Text("EDITAR")
                        }

                        Button(
                            onClick = {
                                personaAEliminar = persona
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("ELIMINAR")
                        }
                    }
                }
            }
        }
    }

    // Dialogo alerta de confirmacion eliminacion
    if (personaAEliminar != null) {

        AlertDialog(
            onDismissRequest = {
                personaAEliminar = null
            },

            title = {
                Text("Eliminar persona")
            },

            text = {
                Text(
                    "¿Está seguro de eliminar a ${personaAEliminar?.nombre}?"
                )
            },

            confirmButton = {

                Button(
                    onClick = {

                        personaAEliminar?.let { persona ->

                            viewModel.eliminarPersona(persona.id)
                        }

                        personaAEliminar = null
                    }
                ) {
                    Text("ELIMINAR")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        personaAEliminar = null
                    }
                ) {
                    Text("CANCELAR")
                }
            }
        )
    }

    // Dialogo alerta de confirmacion edicion
    if (personaAEditar != null) {

        AlertDialog(
            onDismissRequest = {
                personaAEditar = null
            },

            title = {
                Text("Editar persona")
            },

            text = {

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    // Nombre
                    OutlinedTextField(
                        value = nombreEditar,
                        onValueChange = {
                            nombreEditar = it
                        },
                        label = {
                            Text("Nombre")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Pais
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        OutlinedButton(
                            onClick = {
                                menuPaisesEditarAbierto = true
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text = if (paisEditar.isEmpty()) {
                                    "Seleccione un país"
                                } else {
                                    paisEditar
                                }
                            )
                        }


                        DropdownMenu(
                            expanded = menuPaisesEditarAbierto,
                            onDismissRequest = {
                                menuPaisesEditarAbierto = false
                            }
                        ) {

                            paises.forEach { opcion ->

                                DropdownMenuItem(
                                    text = {
                                        Text(opcion)
                                    },

                                    onClick = {

                                        paisEditar = opcion

                                        menuPaisesEditarAbierto = false
                                    }
                                )
                            }
                        }
                    }

                    // Direccion
                    OutlinedTextField(
                        value = direccionEditar,
                        onValueChange = {
                            direccionEditar = it
                        },
                        label = {
                            Text("Dirección")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Estado civil
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text("Estado civil")

                        Switch(
                            checked = estadoCivilEditar,
                            onCheckedChange = {
                                estadoCivilEditar = it
                            }
                        )
                    }

                    // Trabajo
                    OutlinedTextField(
                        value = trabajoEditar,
                        onValueChange = {
                            trabajoEditar = it
                        },
                        label = {
                            Text("Trabajo")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Error
                    if (mensajeErrorEditar.isNotEmpty()) {

                        Text(
                            text = mensajeErrorEditar,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            },
            confirmButton = {

                Button(
                    onClick = {

                        if (nombreEditar.isBlank()) {

                            mensajeErrorEditar =
                                "El nombre no puede estar vacío"

                        } else if (paisEditar.isBlank()) {

                            mensajeErrorEditar =
                                "Debe seleccionar un país"

                        } else if (direccionEditar.isBlank()) {

                            mensajeErrorEditar =
                                "La dirección no puede estar vacía"

                        } else if (trabajoEditar.isBlank()) {

                            mensajeErrorEditar =
                                "El trabajo no puede estar vacío"

                        } else {

                            personaAEditar?.let { persona ->

                                val personaEditada = Persona(
                                    createdAt = persona.createdAt,
                                    nombre = nombreEditar,
                                    pais = paisEditar,
                                    direccion = direccionEditar,
                                    estadocivil = estadoCivilEditar,
                                    trabajo = trabajoEditar,
                                    id = persona.id
                                )

                                viewModel.actualizarPersona(
                                    persona.id,
                                    personaEditada
                                )
                            }
                            personaAEditar = null
                        }
                    }
                ) {
                    Text("GUARDAR")
                }
            },
            dismissButton = {

                TextButton(
                    onClick = {
                        personaAEditar = null
                    }
                ) {
                    Text("CANCELAR")
                }
            }
        )
    }
}