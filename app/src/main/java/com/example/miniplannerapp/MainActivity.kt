package com.example.miniplannerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.miniplannerapp.navigation.AppNavHost
import com.example.miniplannerapp.ui.theme.MiniPlannerAppTheme
import com.example.miniplannerapp.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {

    private val vm: TaskViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TaskViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiniPlannerAppTheme {
                AppNavHost(vm)
            }
        }
    }
}