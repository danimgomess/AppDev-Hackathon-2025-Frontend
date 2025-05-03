package com.example.appdevhackathon2025frontend.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdevhackathon2025frontend.model.ItemRepository
import com.example.appdevhackathon2025frontend.model.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {

    val uiStateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState())


    data class UiState(
        val ids: List<String> = emptyList(),
        val items: List<ItemRepository.Item?> = emptyList()
    )

    init {
        viewModelScope.launch {
            val ids = itemRepository.getItemIds()
            val items = ids.map { id ->
                itemRepository.getItemFromId(id)
            }
            uiStateFlow.value = uiStateFlow.value.copy(ids = ids, items = items)
        }
    }

    fun refreshItems() {
        viewModelScope.launch{
            val itemIds = itemRepository.getItemIds()
            uiStateFlow.value = uiStateFlow.value.copy(
                items = itemIds.map { null },
                ids = itemIds
            )

            for (i in itemIds.indices) {
                val id = itemIds[i]
                launch {
                    val item = itemRepository.getItemFromId(id)
                    uiStateFlow.value = uiStateFlow.value.copy(
                        items = uiStateFlow.value.items.toMutableList().apply {
                            this[i] = item
                        }
                    )
                }
            }
        }
    }
}