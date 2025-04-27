package com.example.appdevhackthon2025.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {
    val uiStateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState())

    data class UiState(
        val ids: List<String> = emptyList(),
    )

}