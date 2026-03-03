package com.example.miniplannerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miniplannerapp.ui.screens.AddTaskScreen
import com.example.miniplannerapp.ui.screens.TaskListScreen
import com.example.miniplannerapp.viewmodel.TaskViewModel

@Composable
fun AppNavHost(vm: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LIST
    ) {
        composable(NavRoutes.LIST) {
            TaskListScreen(
                tasks = vm.tasks,
                onAddClick = { navController.navigate(NavRoutes.ADD) },
                onToggleDone = { id, done -> vm.toggleDone(id, done) },
                onDelete = { id -> vm.deleteTask(id) }
            )
        }

        composable(NavRoutes.ADD) {
            AddTaskScreen(
                onSave = { text, date ->
                    vm.addTask(text, date)
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}