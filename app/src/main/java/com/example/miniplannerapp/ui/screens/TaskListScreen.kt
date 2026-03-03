package com.example.miniplannerapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miniplannerapp.data.Task

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onAdd: (text: String, date: String?) -> Unit,
    onToggleDone: (id: String, done: Boolean) -> Unit,
    onDelete: (id: String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Task") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date (optional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                onAdd(text, date)
                text = ""
                date = ""
            },
            enabled = text.trim().isNotEmpty()
        ) { Text("Add") }

        Divider()

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tasks, key = { it.id }) { task ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { onToggleDone(task.id, it) }
                    )
                    Column(Modifier.weight(1f)) {
                        Text(task.text)
                        task.date?.let { Text(it, style = MaterialTheme.typography.bodySmall) }
                    }
                    TextButton(onClick = { onDelete(task.id) }) { Text("Delete") }
                }
            }
        }
    }
}