package com.example.miniplannerapp.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.miniplannerapp.ui.util.formatDate
import java.time.LocalDate
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import com.example.miniplannerapp.data.AppConstants.COLUMN_VERTICAL_ARRANGEMENT
import com.example.miniplannerapp.data.AppConstants.PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    onSave: (text: String, date: String?) -> Unit,
    onCancel: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    val context = LocalContext.current
    val initial = selectedDate ?: LocalDate.now()

    val datePickerDialog = remember(selectedDate) {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            },
            initial.year,
            initial.monthValue - 1,
            initial.dayOfMonth
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add task") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(PADDING.dp),
            verticalArrangement = Arrangement.spacedBy(COLUMN_VERTICAL_ARRANGEMENT.dp)
        ) {

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Task") },
                modifier = Modifier.fillMaxWidth()
            )

            // Date (optional): readOnly + opens DatePickerDialog
            OutlinedTextField(
                value = formatDate(selectedDate),
                onValueChange = {},
                label = { Text("Date (optional)") },
                readOnly = true,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { datePickerDialog.show() }) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "Pick date"
                        )
                    }
                }
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        val dateString = formatDate(selectedDate).ifBlank { null }
                        onSave(text, dateString)
                    },
                    enabled = text.trim().isNotEmpty()
                ) { Text("Save") }

                OutlinedButton(onClick = onCancel) { Text("Cancel") }
            }
        }
    }
}