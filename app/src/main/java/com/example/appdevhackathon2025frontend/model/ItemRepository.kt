package com.example.appdevhackathon2025frontend.model

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.util.Log.e
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.ByteArrayOutputStream

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
    private val localToRemoteIdMap = mutableMapOf<String, Int>()
    private val remoteToLocalIdMap = mutableMapOf<Int, String>()

    private val idSequence = mutableListOf<String>()

    /** Given an [id], returns the [FormUiState] associated with it. */
    suspend fun getItemFromId(id: String): Item? {
        return itemMap[id]
    }

    /** Gets all the formState ids, ordered by chronology. */
    suspend fun getItemIds(): List<String> {
        return idSequence.toList()
    }

    suspend fun getRemoteIdFromLocalId(localId: String): Int? {
        return localToRemoteIdMap[localId]
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
        return id
    }

    suspend fun postAndCacheItem(localId: String): Result<Int> {
        val item = itemMap[localId] ?: return Result.failure(IllegalArgumentException("No such local item"))
        return postNewItemRemote(item).onSuccess { remoteId ->
            // Sync maps
            localToRemoteIdMap[localId] = remoteId.toInt()
            remoteToLocalIdMap[remoteId.toInt()] = localId
        }
    }


    fun String.toPlainRequestBody(): RequestBody =
        this.toRequestBody("text/plain".toMediaTypeOrNull())

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
                val remoteItems = response.body()?.items?.mapNotNull {
                    // Basic validation check: skip items with blank fields
                    if (
                        it.user.name.isBlank() ||
                        it.user.email.isBlank() ||
                        it.user.phone.isBlank() ||
                        it.title.isBlank() ||
                        it.location.isBlank() ||
                        it.description.isBlank() ||
                        it.timeFound.isBlank() ||
                        it.imageLink.isNullOrBlank()
                    ) {
                        null
                    } else {
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
                    }
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
                        phone = it.user.phone.toString(),
                        title = it.title,
                        location = it.location,
                        description = it.description,
                        timeFound = it.timeFound,
                        picture = loadImageFromURL(it.imageLink.toString())
                    )
                )
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun syncAllItemsFromRemote(): Result<Unit> {
        return fetchAllItemsRemote().map { remoteItems ->
            itemMap.clear()
            idSequence.clear()
            for ((_, item) in remoteItems.withIndex()) {
                val id = generateItemId()
                itemMap[id] = item
                idSequence.add(id)
            }
        }
    }

    fun imageBitmapToMultipartOrNull(
        image: ImageBitmap?,
        partName: String = "image",
        fileName: String = "image.jpg"
    ): MultipartBody.Part? {
        return try {
            if (image == null) return null

            val bitmap = image.asAndroidBitmap()
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            val byteArray = stream.toByteArray()

            val requestBody = byteArray.toRequestBody("image/jpeg".toMediaTypeOrNull())
            MultipartBody.Part.createFormData(partName, fileName, requestBody)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Remote: Add new item
    suspend fun postNewItemRemote(localItem: Item): Result<Int> {
        val imagePart = localItem.picture?.let {
            imageBitmapToMultipartOrNull(it)
        }
        return try {
            val response = retrofitInstance.apiService.addItemWithImage(
                name = localItem.name.toPlainRequestBody(),
                email = localItem.email.toPlainRequestBody(),
                phone = localItem.phone.toPlainRequestBody(),
                title = localItem.title.toPlainRequestBody(),
                location = localItem.location.toPlainRequestBody(),
                description = localItem.description.toPlainRequestBody(),
                timeFound = localItem.timeFound.toPlainRequestBody(),
                image = imagePart
            )
            if (response.isSuccessful) {
                val itemResponse = response.body()
                if (itemResponse != null) {
                    val backendId = itemResponse.id
                    Result.success(backendId)
                } else {
                    Result.failure(IllegalStateException("Empty response body"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                e("ItemRepository", "Unsuccessful response: $errorBody")

                Log.d("ItemRepository", "Posted new item to remote but was not successful")
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    // Remote: Create new user : might not be used.
    suspend fun registerUserRemote(localItem: Item): Result<Unit> {
        return try {
            val userRequest = UserRequest(
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
