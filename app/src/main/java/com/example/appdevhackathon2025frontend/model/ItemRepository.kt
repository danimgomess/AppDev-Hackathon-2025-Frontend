package com.example.appdevhackathon2025frontend.model

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.appdevhackathon2025frontend.viewmodel.FormViewModel.FormUiState
import javax.inject.Inject
import javax.inject.Singleton
import com.example.appdevhackathon2025frontend.retrofit.*
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException

@Singleton
class ItemRepository @Inject constructor(
    private val retrofitInstance: RetrofitInstance,
    @ApplicationContext private val context: Context
) {
    data class Item(
        val name: String,
        val email: String,
        val phone: String,
        val title: String,
        val location: String,
        val description: String,
        val timeFound: String,
        val picture: ImageBitmap? = null // Optional picture
    )

    private val itemMap = mutableMapOf<String, Item>()

    private val idSequence = mutableListOf<String>()

    /** Given an [id], returns the [FormUiState] associated with it. */
    suspend fun getItemFromId(id: String): Item? {
        return itemMap[id]
    }

    /** Gets all the formState ids, ordered by chronology. */
    suspend fun getItemIds(): List<String> {
        return idSequence.toList()
    }

    /** Generates a new unique id for an formState; one that is not already in use. */
    private fun generateItemId(): String {
        // "XXXX-XXXX-XXXX"
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        val makeFourSeq = { (1..4).map { chars.random() }.joinToString("") }

        var key = "${makeFourSeq()}-${makeFourSeq()}-${makeFourSeq()}"
        while (itemMap.containsKey(key)) {
            key = "${makeFourSeq()}-${makeFourSeq()}-${makeFourSeq()}"
        }
        return key
    }

    /** Saves a new Item and returns its ID. */
    suspend fun saveItem(item: Item): String {
        val id = generateItemId()
        itemMap[id] = item
        idSequence.add(id)
//        postNewItemRemote(item)
        return id
    }

    /**
     * Loads and returns an image from a URL using Coil.
     */
    suspend fun loadImageFromURL(url: String): ImageBitmap {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .build()

        val result = imageLoader.execute(request)
        if (result is SuccessResult) {
            return (result.drawable.toBitmap()).asImageBitmap()
        }

        Log.e("CoilRepository", "Failed to load image from URL: $url")

        return ImageBitmap(1, 1)
    }
    // Remote: Fetch all items from server
    suspend fun fetchAllItemsRemote(): Result<List<Item>> {
        return try {
            val response = retrofitInstance.apiService.getAllItems()
            if (response.isSuccessful) {
                val remoteItems = response.body()?.items?.map {
                    Item(
                        name = it.user.name,
                        email = it.user.email,
                        phone = it.user.phone,
                        title = it.title,
                        location = it.location,
                        description = it.description,
                        timeFound = it.timeFound,
                        picture = loadImageFromURL(it.imageLink)
                    )
                } ?: emptyList()
                Result.success(remoteItems)
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    // Remote: Get item by ID
    suspend fun fetchItemByIdRemote(id: Int): Result<Item> {
        return try {
            val response = retrofitInstance.apiService.getItemById(id)
            if (response.isSuccessful) {
                val it = response.body()!!
                Result.success(
                    Item(
                        name = it.user.name,
                        email = it.user.email,
                        phone = it.user.phone,
                        title = it.title,
                        location = it.location,
                        description = it.description,
                        timeFound = it.timeFound,
                        picture = loadImageFromURL(it.imageLink)
                    )
                )
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    // Remote: Add new item
    suspend fun postNewItemRemote(localItem: Item): Result<Unit> {
//        val imageLink = try{
//            //Upload image and get link
//        }
        return try {
            val postItem = ItemRequest(
                name = localItem.name,
                email = localItem.email,
                phone = localItem.phone,
                title = localItem.title,
                location = localItem.location,
                description = localItem.description,
                timeFound = localItem.timeFound,
                imageLink = "imageLink", // TODO: image uploading
            )
            val response = retrofitInstance.apiService.addItem(postItem)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    // Remote: Create new user : might not be used.
    suspend fun registerUserRemote(localItem: Item): Result<Unit>   {
        return try {
            val userRequest = User(
                name = localItem.name,
                email = localItem.email,
                phone = localItem.phone
            )
            val response = retrofitInstance.apiService.createUser(userRequest)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    /** Deletes an formState given its id. */
    suspend fun deleteItem(id: String) {
        itemMap.remove(id)
        idSequence.remove(id)
    }
}
