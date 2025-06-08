package com.example.statecheck

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ReminderDetailRoot(
    modifier: Modifier = Modifier,
    onClickEditTitle: () -> Unit,
    onClickEditDescription: () -> Unit,
    reminderViewModel: SharedReminderViewModel = viewModel<SharedReminderViewModel>()
) {
    val state by reminderViewModel.stateTwo.collectAsStateWithLifecycle()

    DetailScreen(
        modifier = modifier,
        state = state,
        itemType = "REMINDER",
        onAction = { action ->
            when(action) {
                StateActions.NavigateToDescriptionEdit -> onClickEditDescription()
                StateActions.NavigateToTitleEdit -> onClickEditTitle()
                else -> Unit
            }
            reminderViewModel.executeActions(action)
        }
    )
}

@Composable
fun ReminderEditTitleRoot(
    modifier: Modifier = Modifier,
    onClickSave: () -> Unit,
    reminderViewModel: SharedReminderViewModel = viewModel<SharedReminderViewModel>()
) {
    val state by reminderViewModel.stateTwo.collectAsStateWithLifecycle()

    EditTitleScreen(
        modifier = modifier,
        state = state,
        itemToEdit = "Title",
        itemType = "REMINDER",
        onAction = { action ->
            when(action) {
                StateActions.SaveItemUpdates -> onClickSave()
                else -> Unit
            }
            reminderViewModel.executeActions(action)
        },
    )
}

@Composable
fun ReminderEditDescriptionRoot(
    modifier: Modifier = Modifier,
    onClickSave: () -> Unit,
    reminderViewModel: SharedReminderViewModel = viewModel<SharedReminderViewModel>()
) {
    val state by reminderViewModel.stateTwo.collectAsStateWithLifecycle()

    EditDescriptionScreen(
        modifier = modifier,
        state = state,
        itemToEdit = "Description",
        itemType = "REMINDER",
        onAction = { action ->
            when(action) {
                StateActions.SaveItemUpdates -> onClickSave()
                else -> Unit
            }
            reminderViewModel.executeActions(action)
        },
    )
}
