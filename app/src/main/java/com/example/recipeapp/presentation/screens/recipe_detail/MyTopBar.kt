package com.example.recipeapp.presentation.screens.recipe_detail

import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(title: String, onBack: () -> Unit, icon: ImageVector) {
    TopAppBar(
        title = {
            Text(title, fontWeight = FontWeight.Bold)
        },
        navigationIcon = {
            IconButton(onBack) {
                Icon(imageVector = icon, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.DarkGray,
            navigationIconContentColor = Color.DarkGray

        )
    )
}