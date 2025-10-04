package com.umg.actividadclase11.data.model

data class Product(
    val id: Long? = null,         // Opcional: se asigna en el backend
    val name: String,
    val price: Double,
    val category: String?,
    val createdAt: String? = null // Opcional: se genera en el backend
)