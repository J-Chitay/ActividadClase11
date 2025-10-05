package com.umg.actividadclase11.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umg.actividadclase11.data.model.ProductRequest
import com.umg.actividadclase11.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    // Crear internamente el repositorio
    private val repository = ProductRepository()

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState

    // Cargar todos los productos
    fun loadAll() {
        _uiState.update { it.copy(loading = true, message = null) }
        viewModelScope.launch {
            runCatching {
                repository.listAll()
            }.onSuccess { products ->
                _uiState.update { it.copy(items = products, loading = false) }
            }.onFailure { e ->
                _uiState.update { it.copy(loading = false, message = e.message) }
            }
        }
    }

    // Buscar productos por nombre
    fun searchByName(name: String) {
        _uiState.update { it.copy(loading = true, message = null) }
        viewModelScope.launch {
            runCatching {
                repository.search(name)
            }.onSuccess { results ->
                _uiState.update { it.copy(items = results, loading = false) }
            }.onFailure { e ->
                _uiState.update { it.copy(loading = false, message = e.message) }
            }
        }
    }

    // Crear producto
    fun create(product: ProductRequest) {
        viewModelScope.launch {
            runCatching { repository.create(product) }
                .onSuccess { loadAll() }
                .onFailure { e -> _uiState.update { it.copy(message = e.message) } }
        }
    }

    // Actualizar producto
    fun update(id: Long, product: ProductRequest) {
        viewModelScope.launch {
            runCatching { repository.update(id, product) }
                .onSuccess { loadAll() }
                .onFailure { e -> _uiState.update { it.copy(message = e.message) } }
        }
    }

    // Eliminar producto
    fun delete(id: Long) {
        viewModelScope.launch {
            runCatching { repository.delete(id) }
                .onSuccess { loadAll() }
                .onFailure { e -> _uiState.update { it.copy(message = e.message) } }
        }
    }
}