package com.example.meritmatch.navigation

sealed class Screens(val route: String) {
    data object LandingPage: Screens(route = "LandingPage")
    data object LoginPage: Screens(route = "LoginPage")
    data object SignUpPage: Screens(route = "SignUpPage")
    data object HomePage: Screens(route = "HomePage")
    data object AccountPage: Screens(route = "AccountPage")
    data object TaskPage: Screens(route = "TaskPage")
    data object NewTaskPage: Screens(route = "NewTaskPage")
}