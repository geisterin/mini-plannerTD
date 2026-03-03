package com.example.miniplannerapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddTaskScreen(
    onSave: (text: String, date: String?) -> Unit,
    onCancel: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Add task", style = MaterialTheme.typography.headlineSmall)

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

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { onSave(text, date) },
                    enabled = text.trim().isNotEmpty()
                ) { Text("Save") }

                OutlinedButton(onClick = onCancel) { Text("Cancel") }
            }
        }
    }
}