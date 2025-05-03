package com.example.appdevhackathon2025frontend.retrofit

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("items/")
    suspend fun getAllItems(): Response<ItemsResponse>

    @GET("items/{id}/")
    suspend fun getItemById(
        @retrofit2.http.Path("id") id: Int
    ): Response<ItemResponse>

    @POST("add_item/")
    suspend fun addItem(
        @Body newItem: ItemRequest
    ): Response<ItemResponse>

    @POST("users/")
    suspend fun createUser(
        @Body user: User
    ): Response<User>

}


data class ItemsResponse(
    val items: List<ItemResponse>
)

data class User(
    @SerializedName("username")
    val name: String,
    val email: String,
    val phone: String
)

data class ItemResponse(
    val user: User,
    val title: String,
    val location: String,
    val description: String,
    @SerializedName("date_found")
    val timeFound: String,
    @SerializedName("image_url")
    val imageLink: String
)

data class ItemRequest(
    val name: String,
    val email: String,
    @SerializedName("phone_number")
    val phone: String,
    val title: String,
    @SerializedName("location_found")
    val location: String,
    val description: String,
    val timeFound: String,
    @SerializedName("image")
    val imageLink: String
)

//TODO (Optional): an empty version of your data class.