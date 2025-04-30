package com.example.appdevhackathon2025frontend.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle,
//    private val repository: ItemRepository,
) : ViewModel() {
//    val id: String = checkNotNull(savedStateHandle["id"])
    private val _uiState = MutableStateFlow(FormUiState())
    val uiState = _uiState

    data class FormUiState(
        val name: String = "Name",
        val email: String = "email",
        val phone: String = "phone",
        val title: String = "title",
        val location: String = "location",
        val description: String = "desc",
        val timeFound: String = "ttf",
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
//
//    fun submitForm() {
//        repository.saveFormState(_uiState.value)
//    }


    init {
        viewModelScope.launch {

            launch {
                delay(50)
            }
        }
    }
}
