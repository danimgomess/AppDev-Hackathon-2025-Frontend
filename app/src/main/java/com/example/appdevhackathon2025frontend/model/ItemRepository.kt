package com.example.appdevhackathon2025frontend.model

import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import com.example.appdevhackathon2025frontend.viewmodel.FormViewModel.FormUiState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(
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
            Log.d("ItemRepository", itemMap.toString())
            Log.d("ItemRepository", "getItemFromId: $id")
            Log.d("ItemRepository", "getItemFromId: ${itemMap[id]}")
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
            Log.d("ItemRepository", "saveItem: $item")
            val id = generateItemId()
            Log.d("ItemRepository", "saveItem: $id")
            itemMap[id] = item
            Log.d("ItemRepository", "saveItem: ${itemMap[id]}")
            idSequence.add(id)
            return id
    }

    /** Deletes an formState given its id. */
    suspend fun deleteItem(id: String) {
        itemMap.remove(id)
        idSequence.remove(id)
    }
}
