package com.example.meritmatch.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.screens.AccountPage
import com.example.meritmatch.screens.HomePage
import com.example.meritmatch.screens.LandingPage
import com.example.meritmatch.screens.LoginPage
import com.example.meritmatch.screens.NewTaskPage
import com.example.meritmatch.screens.SignUpPage
import com.example.meritmatch.screens.TaskPage

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel:MainViewModel = viewModel<MainViewModel>()
    
    NavHost(navController = navController, startDestination = Screens.LandingPage.route) {
        composable(route = Screens.LandingPage.route) {
            LandingPage(navController = navController)
        }
        composable(route = Screens.LoginPage.route) {
            LoginPage(navController = navController)
        }
        composable(route = Screens.SignUpPage.route) {
            SignUpPage(navController = navController)
        }
        composable(route = Screens.HomePage.route) {
            HomePage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.AccountPage.route) {
            AccountPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.TaskPage.route) {
            TaskPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.NewTaskPage.route) {
            NewTaskPage(navController = navController, viewModel = viewModel)
        }
    }
}