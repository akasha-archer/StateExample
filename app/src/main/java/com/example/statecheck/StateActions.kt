package com.example.statecheck

sealed interface StateActions {
    data object NavigateToTitleEdit: StateActions
    data object NavigateToDescriptionEdit: StateActions
    data class UpdateTitle(val newTitle: String) : StateActions
    data class UpdateDescription(val newDescription: String) : StateActions
    data object SaveItemUpdates: StateActions
}
