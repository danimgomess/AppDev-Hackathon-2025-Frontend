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
class FormViewModel @Inject constructor(
    private val repository: ItemRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FormUiState())
    val uiState = _uiState

    data class FormUiState(
        val name: String = "",
        val email: String = "",
        val phone: String = "",
        val title: String = "",
        val location: String = "",
        val description: String = "",
        val timeFound: String = "",
        val picture: ImageBitmap? = null,
        )
    fun updateName(newName: String) {
        _uiState.value = _uiState.value.copy(name = newName)
    }

    fun updateEmail(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun updatePhone(newPhone: String) {
        _uiState.value = _uiState.value.copy(phone = newPhone)
    }

    fun updateTitle(newTitle: String) {
        _uiState.value = _uiState.value.copy(title = newTitle)
    }

    fun updateLocation(newLocation: String) {
        _uiState.value = _uiState.value.copy(location = newLocation)
    }

    fun updateDescription(newDescription: String) {
        _uiState.value = _uiState.value.copy(description = newDescription)
    }

    fun updateTimeFound(newTimeFound: String) {
        _uiState.value = _uiState.value.copy(timeFound = newTimeFound)
    }

    fun updatePicture(newPicture: ImageBitmap?) {
        _uiState.value = _uiState.value.copy(picture = newPicture)
    }

    fun submitForm() {
        repository.saveFormState(_uiState.value)
    }


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
