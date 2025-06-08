package com.example.statecheck

interface AllState {
    val title: String
    val description: String
    val isEditingItem: Boolean
}

data class StateOne(
    override val title: String = "",
    override val description: String = "",
    override val isEditingItem: Boolean = false
) : AllState

data class StateTwo(
    override val title: String = "",
    override val description: String = "",
    override val isEditingItem: Boolean = false
) : AllState
