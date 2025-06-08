package com.example.statecheck

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Composable
fun NavRoot(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavPlaces.ReminderEditGraph
    ) {
        navigation<NavPlaces.TaskEditGraph>(
            startDestination = NavPlaces.TaskDetail
        ) {
            composable<NavPlaces.TaskDetail> { entry ->
                val viewModel = entry.sharedViewModel<SharedTaskViewModel>(navController)
                TaskDetailRoot(
                    onClickEditTitle = {
                        navController.navigate(NavPlaces.TaskTitle)
                    },
                    onClickEditDescription = {
                        navController.navigate(NavPlaces.TaskDescription)
                    },
                    firstViewModel = viewModel
                )
            }

            composable<NavPlaces.TaskDescription> { entry ->
                val viewModel = entry.sharedViewModel<SharedTaskViewModel>(navController)
                TaskEditDescriptionRoot(
                    onClickSave = {
                        navController.navigateUp()
                    },
                    firstViewModel = viewModel
                )
            }
            composable<NavPlaces.TaskTitle> { entry ->
                val viewModel = entry.sharedViewModel<SharedTaskViewModel>(navController)
                TaskEditTitleRoot(
                    onClickSave = {
                        navController.navigateUp()
                    },
                    firstViewModel = viewModel
                )
            }
        } // END OF TASK GRAPH

        navigation<NavPlaces.ReminderEditGraph>(
            startDestination = NavPlaces.ReminderDetail
        ) {
            composable<NavPlaces.ReminderDetail> { entry ->
                val viewModel = entry.sharedViewModel<SharedReminderViewModel>(navController)
                ReminderDetailRoot(
                    onClickEditTitle = {
                        navController.navigate(NavPlaces.ReminderTitle)
                    },
                    onClickEditDescription = {
                        navController.navigate(NavPlaces.ReminderDescription)
                    },
                    reminderViewModel = viewModel
                )
            }

            composable<NavPlaces.ReminderDescription> { entry ->
                val viewModel = entry.sharedViewModel<SharedReminderViewModel>(navController)
                ReminderEditDescriptionRoot(
                    onClickSave = {
                        navController.navigateUp()
                    },
                    reminderViewModel = viewModel
                )
            }
            composable<NavPlaces.ReminderTitle> { entry ->
                val viewModel = entry.sharedViewModel<SharedReminderViewModel>(navController)
                ReminderEditTitleRoot(
                    onClickSave = {
                        navController.navigateUp()
                    },
                    reminderViewModel = viewModel
                )
            }
        } // END OF REMINDER GRAPH
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

sealed interface NavPlaces {
    @Serializable // route for nested graph for Task screens
    data object TaskEditGraph: NavPlaces

    @Serializable // route for nested graph for Task screens
    data object ReminderEditGraph: NavPlaces
    @Serializable
    data object TaskDetail : NavPlaces

    @Serializable
    data object TaskDescription : NavPlaces

    @Serializable
    data object TaskTitle : NavPlaces

    @Serializable
    data object ReminderDetail : NavPlaces

    @Serializable
    data object ReminderDescription : NavPlaces

    @Serializable
    data object ReminderTitle : NavPlaces
}
