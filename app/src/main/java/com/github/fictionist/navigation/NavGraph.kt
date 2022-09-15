package com.github.fictionist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.fictionist.screens.detail.DetailScreen
import com.github.fictionist.screens.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.path
    ) {
        addHomeScreen(navController, this)

        addDetailsScreen(navController, this)
//        addProfileScreen(navController, this)

//        addSearchScreen(navController, this)
    }
}

private fun addHomeScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Home.path) {
        HomeScreen(
            popBackStack = { popUpToHome(navController) },
            detailsScreen = { id -> redirectToDetails(navController, id) }
        )
    }
}

fun redirectToDetails(navController: NavHostController, id: Int) {
    navController.navigate(
        NavRoute.Details.withArgs(
            id.toString()
        )
    )
}

private fun addDetailsScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = NavRoute.Details.withArgsFormat(NavRoute.Details.id),
        arguments = listOf(
            navArgument(NavRoute.Details.id) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val args = navBackStackEntry.arguments
        args?.getInt(NavRoute.Details.id)?.let {
            DetailScreen(
                countryId = it,
                popBackStack = { navController.popBackStack() }
            )
        }
    }
}

private fun popUpToHome(navController: NavHostController) {
    navController.popBackStack(NavRoute.Home.path, inclusive = false)
}
