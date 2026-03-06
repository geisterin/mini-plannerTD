package com.example.miniplannerapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miniplannerapp.data.AppConstants.COLUMN_VERTICAL_ARRANGEMENT
import com.example.miniplannerapp.data.AppConstants.PADDING
import com.example.miniplannerapp.data.Task
import com.example.miniplannerapp.ui.components.TaskItem

import androidx.compose.ui.graphics.Color
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onAddClick: () -> Unit,
    onToggleDone: (id: String, done: Boolean) -> Unit,
    onDelete: (id: String) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Tasks") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Outlined.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(PADDING.dp),
            contentPadding = PaddingValues(bottom = PADDING.dp),
            verticalArrangement = Arrangement.spacedBy(COLUMN_VERTICAL_ARRANGEMENT.dp)
        ) {
            items(items = tasks, key = { it.id }) { task ->
                TaskItem(
                    task = task,
                    onToggleDone = onToggleDone,
                    onDelete = onDelete
                )
            }
        }
    }
}