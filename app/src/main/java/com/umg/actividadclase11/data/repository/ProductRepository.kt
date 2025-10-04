package com.umg.actividadclase11.data.repository
import com.umg.actividadclase11.data.model.Product
import com.umg.actividadclase11.data.model.ProductRequest
import com.umg.actividadclase11.data.network.RetrofitInstance
import retrofit2.Response

class ProductRepository {

    private val api = RetrofitInstance.api

    suspend fun listAll(): List<Product> = api.listAll()

    suspend fun get(id: Long): Product = api.getById(id)

    suspend fun create(product: ProductRequest): Product = api.create(product)

    suspend fun update(id: Long, product: ProductRequest): Product = api.update(id, product)

    suspend fun delete(id: Long): Response<Unit> = api.delete(id)

    suspend fun search(name: String): List<Product> = api.search(name)
}
