package com.umg.actividadclase11.data.network

import com.umg.actividadclase11.data.model.Product
import com.umg.actividadclase11.data.model.ProductRequest
import retrofit2.Response
import retrofit2.http.*

interface ProductApi {

    @GET("/api/products")
    suspend fun listAll(): List<Product>

    @GET("/api/products/{id}")
    suspend fun getById(@Path("id") id: Long): Product

    @POST("/api/products")
    suspend fun create(@Body product: ProductRequest): Product

    @PUT("/api/products/{id}")
    suspend fun update(@Path("id") id: Long, @Body product: ProductRequest): Product

    @DELETE("/api/products/{id}")
    suspend fun delete(@Path("id") id: Long): Response<Unit>

    @GET("/api/products/search")
    suspend fun search(@Query("name") productName: String): List<Product>
}
