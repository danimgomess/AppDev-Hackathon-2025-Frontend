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
class FormViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FormUiState())
    val uiState = _uiState

    data class FormUiState(
        val name: String? = "",
        val email: String = "",
        val phone: String = "",
        val title: String = "",
        val location: String = "",
        val description: String = "",
        val timeFound: String = "",
        val picture: ImageBitmap? = null,
        )

    init {
        viewModelScope.launch {
            val image = null
            _uiState.value = _uiState.value.copy(picture = image)

            launch {
                _uiState.value = _uiState.value.copy(
                    //Do the actual logic here?
                )
            }
        }
    }
}
