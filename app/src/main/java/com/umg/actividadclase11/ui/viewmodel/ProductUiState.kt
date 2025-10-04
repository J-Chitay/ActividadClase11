package com.umg.actividadclase11.ui.viewmodel

import com.umg.actividadclase11.data.model.Product

data class ProductUiState(
    val loading: Boolean = false,
    val items: List<Product> = emptyList(),
    val message: String? = null
)