package com.example.miniplannerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.miniplannerapp.ui.TaskListScreen
import com.example.miniplannerapp.ui.theme.MiniPlannerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniPlannerAppTheme {
                TaskListScreen()
            }
        }
    }
}