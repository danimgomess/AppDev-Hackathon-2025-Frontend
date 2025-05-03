package com.example.appdevhackathon2025frontend.retrofit

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @GET("items/")
    suspend fun getAllItems(): Response<ItemsResponse>

    @GET("item/{id}/")
    suspend fun getItemById(
        @retrofit2.http.Path("id") id: Int
    ): Response<ItemResponse>

    @Multipart
    @POST("add_item/")
    suspend fun addItemWithImage(
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone_number") phone: RequestBody,
        @Part("title") title: RequestBody,
        @Part("location_found") location: RequestBody,
        @Part("description") description: RequestBody,
        @Part("date_found") timeFound: RequestBody,
        @Part image: MultipartBody.Part? = null
    ): Response<ItemResponse>

    @POST("users/")
    suspend fun createUser(
        @Body user: UserRequest
    ): Response<UserResponse>

}


data class ItemsResponse(
    val items: List<ItemResponse>
)

data class UserRequest(
    @SerializedName("username")
    val name: String,
    val email: String,
    val phone: String
)

data class UserResponse(
    @SerializedName("username")
    val name: String,
    val email: String,
    val phone: String
)

data class ItemResponse(
    val id: Int,
    val user: UserResponse,
    val title: String,
    val location: String,
    val description: String,
    @SerializedName("date_found")
    val timeFound: String,
    @SerializedName("image_url")
    val imageLink: String?
)


//TODO (Optional): an empty version of your data class.