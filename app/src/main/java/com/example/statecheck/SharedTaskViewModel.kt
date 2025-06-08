package com.example.statecheck

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SharedTaskViewModel(): ViewModel() {

    init {
        Log.i("SharedStateViewModel", "init")
    }

    private val _stateOne = MutableStateFlow(StateOne())
    val stateOne = _stateOne
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StateOne()
        )

    fun executeActions(action: StateActions) {
        when(action) {
            is StateActions.UpdateTitle -> {
                _stateOne.value = _stateOne.value.copy(title = action.newTitle)
            }
            is StateActions.UpdateDescription -> {
                _stateOne.value = _stateOne.value.copy(description = action.newDescription)
            }

            StateActions.NavigateToDescriptionEdit -> {
                _stateOne.value = _stateOne.value.copy(isEditingItem = true)
            }
            StateActions.NavigateToTitleEdit -> {
                _stateOne.value = _stateOne.value.copy(isEditingItem = true)
            }

            is StateActions.SaveItemUpdates -> {
                _stateOne.value = _stateOne.value.copy(isEditingItem = false)
            }

        }
    }
}
