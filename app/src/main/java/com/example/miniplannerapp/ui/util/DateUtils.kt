package com.example.miniplannerapp.ui.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")

fun formatDate(date: LocalDate?): String {
    return date?.format(formatter) ?: ""
}