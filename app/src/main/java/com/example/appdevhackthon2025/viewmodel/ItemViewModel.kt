package com.example.appdevhackthon2025.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ItemUiState())
    val uiState = _uiState

    data class ItemUiState(
        val imageBitmap: ImageBitmap? = null,
        val details: String = "",
        val description: String = "",
        val contact: String = ""
    )

    fun updateDetails(newDetails: String) {
        _uiState.value = _uiState.value.copy(details = newDetails)
    }

    fun updateDescription(newDescription: String) {
        _uiState.value = _uiState.value.copy(description = newDescription)
    }

    fun updateContact(newContact: String) {
        _uiState.value = _uiState.value.copy(contact = newContact)
    }

    init {
        viewModelScope.launch {
            val image = null
            _uiState.value = _uiState.value.copy(imageBitmap = image)

            launch {
                _uiState.value = _uiState.value.copy(
                    //What to do to do the actual logic here?
                )
            }
        }
    }
}
