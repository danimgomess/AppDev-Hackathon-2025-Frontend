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
    ): Response<Item>

    @POST("add_item/")
    suspend fun addItem(
        @Body newItem: ItemRequest
    ): Response<Item>

    @POST("users/")
    suspend fun createUser(
        @Body user: UserRequest
    ): Response<User>

}

data class ItemRequest(
    val title: String,
    val location: String,
    val description: String,
    @SerializedName("date_found")
    val timeFound: String,
    @SerializedName("image_link")
    val imageLink: String
)

data class ItemsResponse(
    val items: List<Item>
)

data class UserRequest(
    @SerializedName("username")
    val name: String,
    val email: String,
    val phone: String
)

data class User(
    @SerializedName("username")
    val name: String,
    val email: String,
    val phone: String
)

data class Item(
    val user: User,
    val title: String,
    val location: String,
    val description: String,
    @SerializedName("date_found")
    val timeFound: String,
    @SerializedName("image_link")
    val imageLink: String
)
//TODO 3: Data class that models what the API will return

//TODO (Optional): an empty version of your data class.