package dev.kiryao.ethertree.home.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.kiryao.ethertree.home.presentation.HomeScreenRoute

const val HOME_ROUTE = "home"

@Composable
fun HomeScreenNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE
    ) {
        composable(route = HOME_ROUTE) {
            HomeScreenRoute()
        }
    }
}
