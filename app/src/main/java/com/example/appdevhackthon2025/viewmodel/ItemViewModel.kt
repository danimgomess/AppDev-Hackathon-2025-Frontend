package com.example.appdevhackthon2025.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevhackthon2025.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ItemRepository,
    ) : ViewModel() {

    val id: String = checkNotNull(savedStateHandle["id"])
    private val formState = repository.getFormState()
    //We will use id to get the actual values from a repo?

    data class ItemUiState(
        val imageBitmap: ImageBitmap? = null,
        val details: String = "",
        val description: String = "",
        val contact: String = ""
    )
    private val _uiState = MutableStateFlow(
        ItemUiState(
            details = formState?.title.orEmpty(),
            description = formState?.description.orEmpty(),
            contact = formState?.email.orEmpty()
        )
    )
    val uiState = _uiState

    init {
        viewModelScope.launch {
            val image = null
            _uiState.value = _uiState.value.copy(imageBitmap = image)

            launch {
                _uiState.value = _uiState.value.copy(
                    details = "", description = "",
                    contact = ""
                )
            }
        }
    }
}
