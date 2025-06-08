package com.example.statecheck

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    onAction: (StateActions) -> Unit = {},
    state: AllState,
    itemType: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = itemType,
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge.copy(
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { onAction(StateActions.NavigateToDescriptionEdit) },
            modifier = Modifier
                .fillMaxWidth()
                .align(androidx.compose.ui.Alignment.CenterHorizontally),
            content = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Edit description",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
            }
        )
        TextButton(
            onClick = { onAction(StateActions.NavigateToTitleEdit) },
            modifier = Modifier
                .fillMaxWidth()
                .align(androidx.compose.ui.Alignment.CenterHorizontally),
            content = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Edit title",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
            }
        )
        Text(
            text = state.title.ifEmpty { "No title" },
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = state.description.ifEmpty { "No description" },
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge.copy(
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}

@Composable
fun EditTitleScreen(
    modifier: Modifier = Modifier,
    state: AllState,
    onAction: (StateActions) -> Unit = {},
    itemToEdit: String = "",
    itemType: String
) {
    var newTitle by remember { mutableStateOf(state.title) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = itemType,
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge.copy(
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = {
                onAction(StateActions.SaveItemUpdates)
                onAction(StateActions.UpdateTitle(newTitle))
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(androidx.compose.ui.Alignment.CenterHorizontally),
            content = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Edit",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Save changes",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
            }
        )
        Text(
            text = "Edit $itemToEdit",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = newTitle,
            onValueChange = {
                newTitle = it
            },
            modifier = Modifier
                .fillMaxSize(),
            textStyle = androidx.compose.material3.MaterialTheme.typography.bodyLarge.copy(
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}


@Composable
fun EditDescriptionScreen(
    modifier: Modifier = Modifier,
    state: AllState,
    onAction: (StateActions) -> Unit = {},
    itemToEdit: String = "",
    itemType: String = "TASKS"
) {
    var newDescription by remember { mutableStateOf(state.description) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = itemType,
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge.copy(
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = {
                onAction(StateActions.UpdateDescription(newDescription))
                onAction(StateActions.SaveItemUpdates)
                      },
            modifier = Modifier
                .fillMaxWidth()
                .align(androidx.compose.ui.Alignment.CenterHorizontally),
            content = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Edit",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Save changes",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
            }
        )
        Text(
            text = "Edit $itemToEdit",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = newDescription,
            onValueChange = {
                newDescription = it
            },
            modifier = Modifier
                .fillMaxSize(),
            textStyle = androidx.compose.material3.MaterialTheme.typography.bodyLarge.copy(
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}

@Preview(showBackground =  true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        state = StateOne(
            title = "Title",
            description = "Description"
        ),
        itemType = "TASKS"
    )
}

@Preview(showBackground = true)
@Composable
fun EditTitleScreenPreview() {
    EditTitleScreen(
        state = StateOne(
            title = "Title",
            description = "Description"
        ),
        itemToEdit = "Title",
        itemType = "TASKS"
    )
}

@Preview(showBackground = true)
@Composable
fun EditDescriptionScreenPreview() {
    EditDescriptionScreen(
        state = StateOne(
            title = "Description",
            description = "Description"
        ),
        itemToEdit = "Description",
        itemType = "TASKS"
    )
}