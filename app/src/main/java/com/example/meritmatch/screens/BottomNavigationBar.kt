package com.example.meritmatch.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.navigation.Screens
import com.example.meritmatch.ui.theme.NavBarColor

@Composable
fun BottomNavigationBar(viewModel: MainViewModel, navController: NavController) {
    NavigationBar(
        containerColor = NavBarColor,
        contentColor = Color.White
    ) {
        //profile
        NavigationBarItem(
            selected = viewModel.selectedNavBarIndex == 0,
            onClick = {
                viewModel.selectedNavBarIndex = 0
                navController.navigate(Screens.AccountPage.route)
            },
            icon = {
                if (viewModel.selectedNavBarIndex == 0) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Profile",
                        modifier = Modifier.size(34.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Profile",
                        modifier = Modifier.size(34.dp)
                    )
                }
            },
            label = {
                Text(text = "Profile")
            },
            alwaysShowLabel = false,
            colors = NavigationBarItemColors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White,
                selectedIndicatorColor = Color.Transparent,
                disabledTextColor = Color.White,
                disabledIconColor = Color.White
            )
        )
        //home
        NavigationBarItem(
            selected = viewModel.selectedNavBarIndex == 1,
            onClick = {
                viewModel.selectedNavBarIndex = 1
                navController.navigate(Screens.HomePage.route)
            },
            icon = {
                if (viewModel.selectedNavBarIndex == 1) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home",
                        modifier = Modifier.size(34.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "Home",
                        modifier = Modifier.size(34.dp)
                    )
                }
            },
            label = {
                Text(text = "Home")
            },
            alwaysShowLabel = false,
            colors = NavigationBarItemColors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White,
                selectedIndicatorColor = Color.Transparent,
                disabledTextColor = Color.White,
                disabledIconColor = Color.White
            )
        )
        //Settings
        NavigationBarItem(
            selected = viewModel.selectedNavBarIndex == 2,
            onClick = {
                viewModel.selectedNavBarIndex = 2
                navController.navigate(Screens.SettingsPage.route)
            },
            icon = {
                if (viewModel.selectedNavBarIndex == 2) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(34.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(34.dp)
                    )
                }
            },
            label = {
                Text(text = "Settings")
            },
            alwaysShowLabel = false,
            colors = NavigationBarItemColors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White,
                selectedIndicatorColor = Color.Transparent,
                disabledTextColor = Color.White,
                disabledIconColor = Color.White
            )
        )
    }
}