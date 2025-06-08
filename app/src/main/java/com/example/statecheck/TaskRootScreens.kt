package com.example.statecheck

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskDetailRoot(
    modifier: Modifier = Modifier,
    onClickEditTitle: () -> Unit,
    onClickEditDescription: () -> Unit,
    firstViewModel: SharedTaskViewModel = viewModel<SharedTaskViewModel>()
) {
    val state by firstViewModel.stateOne.collectAsStateWithLifecycle()

    DetailScreen(
        modifier = modifier,
        state = state,
        itemType = "TASKS",
        onAction = { action ->
            when(action) {
                StateActions.NavigateToDescriptionEdit -> onClickEditDescription()
                StateActions.NavigateToTitleEdit -> onClickEditTitle()
                else -> Unit
            }
            firstViewModel.executeActions(action)
        }
    )
}

@Composable
fun TaskEditTitleRoot(
    modifier: Modifier = Modifier   ,
    onClickSave: () -> Unit,
    firstViewModel: SharedTaskViewModel = viewModel<SharedTaskViewModel>()
) {
    val state by firstViewModel.stateOne.collectAsStateWithLifecycle()

    EditTitleScreen(
        modifier = modifier,
        state = state,
        itemToEdit = "Title",
        itemType = "TASKS",
        onAction = { action ->
            when(action) {
                StateActions.SaveItemUpdates -> onClickSave()
                else -> Unit
            }
            firstViewModel.executeActions(action)
        },
    )
}

@Composable
fun TaskEditDescriptionRoot(
    modifier: Modifier = Modifier,
    onClickSave: () -> Unit,
    firstViewModel: SharedTaskViewModel = viewModel<SharedTaskViewModel>()
) {
    val state by firstViewModel.stateOne.collectAsStateWithLifecycle()

    EditDescriptionScreen(
        modifier = modifier,
        state = state,
        itemToEdit = "Description",
        itemType = "TASKS",
        onAction = { action ->
            when(action) {
                StateActions.SaveItemUpdates -> onClickSave()
                else -> Unit
            }
            firstViewModel.executeActions(action)
        },
    )
}