package com.example.appdevhackathon2025frontend.viewmodel

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
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ItemRepository,
) : ViewModel() {

    val id: String = checkNotNull(savedStateHandle["id"])
    //We will use id to get the actual values from a repo?

    data class ItemUiState(
        val imageBitmap: ImageBitmap? = null,
        val details: String? = null,
        val description: String? = null,
        val contact: String? = null
    )

    private val _uiState = MutableStateFlow(ItemUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
//            val remoteResult =  repository.fetchItemByIdRemote(id.toInt())
//            val item = if (remoteResult.isSuccess) {
//                remoteResult.getOrNull()
//            } else {
//                repository.getItemFromId(id) // fallback to local
//            }

            val item = repository.getItemFromId(id)

            item?.let {
                delay(1000)
                _uiState.value = ItemUiState(
                    imageBitmap = it.picture,
                    details = it.title +" found at around "+ it.timeFound +" at "+ it.location,
                    description = it.description,
                    contact = "Found by: "+it.name + "\nPhone: " + it.phone+ "\nEmail: " + it.email
                )
            }
        }
    }
}
