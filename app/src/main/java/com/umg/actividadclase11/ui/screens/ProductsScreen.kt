package com.umg.actividadclase11.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.umg.actividadclase11.data.model.ProductRequest
import com.umg.actividadclase11.ui.viewmodel.ProductViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun ProductsScreen(
    vm: ProductViewModel,
    modifier: Modifier = Modifier  // <-- agregado
) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    var searchText by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {  // <-- usamos el modifier pasado

        // Barra de búsqueda
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Buscar por nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { vm.searchByName(searchText) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Formulario de creación
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Precio") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (name.isNotBlank() && price.isNotBlank()) {
                    vm.create(
                        ProductRequest(
                            name = name,
                            price = price.toDouble(),
                            category = category.ifBlank { null }
                        )
                    )
                    name = ""
                    price = ""
                    category = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Crear")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de productos
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiState.items) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(product.name, style = MaterialTheme.typography.titleMedium)
                            Text("$${product.price}", style = MaterialTheme.typography.bodyMedium)
                            Text(product.category ?: "Sin categoría", style = MaterialTheme.typography.bodySmall)
                        }

                        Row {
                            Button(
                                onClick = {
                                    val updatedPrice = product.price + 1.0
                                    vm.update(
                                        product.id!!,
                                        ProductRequest(product.name, updatedPrice, product.category)
                                    )
                                }
                            ) {
                                Text("+1.00")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(onClick = { vm.delete(product.id!!) }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }

    // Mensajes de estado
    uiState.message?.let { msg ->
        LaunchedEffect(msg) {
            println("Mensaje: $msg") // aquí podrías usar Snackbar en lugar de println
        }
    }
}

