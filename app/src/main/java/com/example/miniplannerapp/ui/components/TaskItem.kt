package com.example.miniplannerapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.miniplannerapp.data.AppConstants.PADDING
import com.example.miniplannerapp.data.Task


@Composable
fun TaskItem(
    task: Task,
    onToggleDone: (id: String, done: Boolean) -> Unit,
    onDelete: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier.padding(PADDING.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.done,
                onCheckedChange = { checked ->
                    onToggleDone(task.id, checked)
                }
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.text,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.done) TextDecoration.LineThrough else null
                )
                if (!task.date.isNullOrBlank()) {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = task.date,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            IconButton(onClick = { onDelete(task.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete task"
                )
            }
        }
    }
}