package com.example.recipeapp.presentation.screens.recipe_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.presentation.components.LoadingIndicator
import com.example.recipeapp.presentation.viewmodels.RecipeDetailViewModel
import com.example.recipeapp.ui.theme.OrangeColor

@Composable
fun RecipeDetailScreen(
    id: Int,
    onBack: () -> Unit,
    viewmodel: RecipeDetailViewModel = viewModel()
) {
    LaunchedEffect(id) {
        viewmodel.fetchRecipeDetails(id)
    }
    Scaffold(
        topBar = {
            MyTopBar(
                "Recipe Details",
                onBack = onBack,
                icon = Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = OrangeColor.copy(alpha = 0.02f))
                .padding(innerPadding)
        ) {
            when {
                viewmodel.isLoading -> LoadingIndicator(1.dp)
                viewmodel.errMsg != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = viewmodel.errMsg ?: "",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold, color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { viewmodel.fetchRecipeDetails(id) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = OrangeColor,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Retry", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}