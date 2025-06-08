package com.example.statecheck

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SharedReminderViewModel(): ViewModel() {
    init {
        Log.i("SharedReminderViewModel", "init")
    }

    private val _stateTwo = MutableStateFlow(StateTwo())
    val stateTwo = _stateTwo
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StateTwo()
        )

    fun executeActions(action: StateActions) {
        when(action) {
            is StateActions.UpdateTitle -> {
                _stateTwo.value = _stateTwo.value.copy(title = action.newTitle)
            }
            is StateActions.UpdateDescription -> {
                _stateTwo.value = _stateTwo.value.copy(description = action.newDescription)
            }

            StateActions.NavigateToDescriptionEdit -> {
                _stateTwo.value = _stateTwo.value.copy(isEditingItem = true)
            }
            StateActions.NavigateToTitleEdit -> {
                _stateTwo.value = _stateTwo.value.copy(isEditingItem = true)
            }

            is StateActions.SaveItemUpdates -> {
                _stateTwo.value = _stateTwo.value.copy(isEditingItem = false)
            }

        }
    }
}
