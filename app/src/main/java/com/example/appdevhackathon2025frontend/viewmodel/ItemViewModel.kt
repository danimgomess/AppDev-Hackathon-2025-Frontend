package com.example.appdevhackathon2025frontend.viewmodel

import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevhackathon2025frontend.model.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ItemRepository,
) : ViewModel() {

    val id: String = checkNotNull(savedStateHandle["id"])

    data class ItemUiState(
        val imageBitmap: ImageBitmap? = null,
        val title: String? = null,
        val details: String? = null,
        val description: String? = null,
        val contact: String? = null
    )

    private val _uiState = MutableStateFlow(ItemUiState())
    val uiState = _uiState.asStateFlow()
    fun convertToHumanReadableFormat(input: String): String? {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        originalFormat.isLenient = false // Strict validation

        val targetFormat = SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a", Locale.getDefault()) // Human-friendly format

        return try {
            // Parse the input string into a Date object
            val date = originalFormat.parse(input)
            // Format the Date object into the target human-readable format
            if (date != null) {
                targetFormat.format(date)
            } else ""
        } catch (e: Exception) {
            // If parsing fails, return null or handle the error as needed
            Log.e("ItemViewModel", "Error parsing date: ${e.message}")
            null
        }
    }


    init {
        viewModelScope.launch {
            val item = repository.getItemFromId(id)
            item?.let {
                delay(1000)
                _uiState.value = ItemUiState(
                    imageBitmap = it.picture,
                    title = it.title,
                    details = "Found on "+ (convertToHumanReadableFormat(it.timeFound)) +"\n on "+ it.location,
                    description = it.description,
                    contact = "Found by: "+it.name + "\nPhone: " + it.phone+ "\nEmail: " + it.email
                )
            }
        }
    }
}
