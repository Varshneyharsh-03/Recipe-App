package com.example.recipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.recipeapp.presentation.screens.home.HomeScreen
import com.example.recipeapp.presentation.screens.recipe_detail.RecipeDetailScreen

@Composable
fun RecipeNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(onRecipeClick = { id -> navController.navigate(RecipeDetailRoute(id)) })
        }
        composable<RecipeDetailRoute> { backStackEntry ->
            RecipeDetailScreen(
                id = backStackEntry.toRoute<RecipeDetailRoute>().recipeId,
                onBack = { navController.popBackStack() })
        }
    }
}