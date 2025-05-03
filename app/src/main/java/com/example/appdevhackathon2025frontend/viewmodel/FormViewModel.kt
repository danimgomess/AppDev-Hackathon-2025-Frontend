package com.example.appdevhackathon2025frontend.viewmodel

import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevhackathon2025frontend.model.ItemRepository
import com.example.appdevhackathon2025frontend.model.ItemRepository.Item
import com.example.appdevhackathon2025frontend.model.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val repository: ItemRepository,
) : ViewModel() {
    val uiStateFlow = MutableStateFlow(FormUiState())

    private val _uiEventFlow = MutableStateFlow<UIEvent<String>?>(null)
    val uiEventFlow = _uiEventFlow.asStateFlow()

    data class FormUiState(
        val name: String = "",
        val email: String = "",
        val phone: String = "",
        val title: String = "",
        val location: String = "",
        val description: String = "",
        val timeFound: String = "",
        val picture: ImageBitmap? = null,
        val id: String? = null,
    )

    fun updateName(newName: String) {
        uiStateFlow.value = uiStateFlow.value.copy(name = newName)
    }

    fun updateEmail(newEmail: String) {
        uiStateFlow.value = uiStateFlow.value.copy(email = newEmail)
    }

    fun updatePhone(newPhone: String) {
        uiStateFlow.value = uiStateFlow.value.copy(phone = newPhone)
    }

    fun updateTitle(newTitle: String) {
        uiStateFlow.value = uiStateFlow.value.copy(title = newTitle)
    }

    fun updateLocation(newLocation: String) {
        uiStateFlow.value = uiStateFlow.value.copy(location = newLocation)
    }

    fun updateDescription(newDescription: String) {
        uiStateFlow.value = uiStateFlow.value.copy(description = newDescription)
    }

    fun updateTimeFound(newTimeFound: String) {
        uiStateFlow.value = uiStateFlow.value.copy(timeFound = newTimeFound)
    }

    fun updatePicture(newPicture: ImageBitmap?) {
        uiStateFlow.value = uiStateFlow.value.copy(picture = newPicture)
    }

    fun onClickUpload() {
        viewModelScope.launch {
            _uiEventFlow.value = UIEvent("launch-image-picker")
        }
    }

    fun onSubmitForm() {
        viewModelScope.launch {
            if (
                uiStateFlow.value.name.isBlank() ||
                uiStateFlow.value.email.isBlank() ||
                uiStateFlow.value.phone.isBlank() ||
                uiStateFlow.value.title.isBlank() ||
                uiStateFlow.value.location.isBlank() ||
                uiStateFlow.value.description.isBlank() ||
                uiStateFlow.value.timeFound.isBlank() ||
                uiStateFlow.value.picture == null
            ) {
                _uiEventFlow.value = UIEvent("form-error:missing-fields")
                return@launch
            }
            val item = Item(
                name = uiStateFlow.value.name,
                email = uiStateFlow.value.email,
                phone = uiStateFlow.value.phone,
                title = uiStateFlow.value.title,
                location = uiStateFlow.value.location,
                description = uiStateFlow.value.description,
                timeFound = uiStateFlow.value.timeFound,
                picture = uiStateFlow.value.picture
            )
            val id = repository.saveItem(item)
            val remoteId = repository.postAndCacheItem(id)
            if (remoteId.isFailure){
                Log.e("FormViewModel", "Failed to post item")
            } else {
                Log.d("FormViewModel", "Successfully posted item ${remoteId.getOrNull()}")
            }
            delay(1000)
            _uiEventFlow.value = UIEvent("navigate-to-item-screen:$id")
        }


    }

    init {
        viewModelScope.launch {
            launch {
                delay(50)
            }
        }
    }
}
