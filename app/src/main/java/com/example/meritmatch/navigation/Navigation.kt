package com.example.meritmatch.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.screens.AccountPage
import com.example.meritmatch.screens.HomePage
import com.example.meritmatch.screens.LandingPage
import com.example.meritmatch.screens.LoginPage
import com.example.meritmatch.screens.NewTaskPage
import com.example.meritmatch.screens.SettingsPage
import com.example.meritmatch.screens.SignUpPage
import com.example.meritmatch.screens.TaskPage

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel:MainViewModel = viewModel<MainViewModel>()
    
    NavHost(
        navController = navController,
        startDestination = Screens.LandingPage.route,

    ) {
        composable(route = Screens.LandingPage.route) {
            LandingPage(navController = navController)
        }
        composable(route = Screens.LoginPage.route) {
            LoginPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.SignUpPage.route) {
            SignUpPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.HomePage.route) {
            HomePage(navController = navController, viewModel  = viewModel)
        }
        composable(route = Screens.AccountPage.route) {
            AccountPage(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screens.TaskPage.route + "/{karma}" + "/{userName}" + "/{content}"+ "/{taskStatus}"+"/{taskId}",
            arguments = listOf(
                navArgument("karma") {
                    type = NavType.IntType
                    nullable = false
                }
                ,
                navArgument("userName") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("content") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("taskStatus") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("taskId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {entry ->
            TaskPage(
                navController = navController,
                viewModel = viewModel,
                karma = entry.arguments?.getInt("karma")!!,
                userName = entry.arguments?.getString("userName")!!,
                content = entry.arguments?.getString("content")!!,
                taskStatus = entry.arguments?.getString("taskStatus")!!,
                taskId = entry.arguments?.getInt("taskId")!!
            )
        }
        composable(route = Screens.NewTaskPage.route) {
            NewTaskPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.SettingsPage.route) {
            SettingsPage(navController = navController, viewModel = viewModel)
        }
    }
}