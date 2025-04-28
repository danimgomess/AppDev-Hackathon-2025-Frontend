package com.example.appdevhackthon2025

import com.example.appdevhackthon2025.viewmodel.FormViewModel.FormUiState
import javax.inject.Inject

class ItemRepository @Inject constructor() {
    private var formState: FormUiState? = null

    fun saveFormState(state: FormUiState) {
        formState = state
    }

    fun getFormState(): FormUiState? = formState
}
