package com.example.recipeapp.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.presentation.components.LoadingIndicator
import com.example.recipeapp.presentation.viewmodels.HomeViewModel
import com.example.recipeapp.ui.theme.OrangeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onRecipeClick: (Int) -> Unit, viewmodel: HomeViewModel = viewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "Recipes",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            })
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            when {
                viewmodel.loading -> LoadingIndicator(2.dp)

                viewmodel.errMsg.isNotEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = viewmodel.errMsg,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold, color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { viewmodel.fetchAllRecipes() },
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

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item(span = { GridItemSpan(currentLineSpan = maxLineSpan) }) {
                            HeaderHome()
                        }

                        if (viewmodel.allCategories.size > 1) {
                            item(span = { GridItemSpan(currentLineSpan = maxLineSpan) }) {
                                CategorySection(
                                    categories = viewmodel.allCategories,
                                    selected = viewmodel.selectCategory,
                                    onSelected = viewmodel::changeSelectedCategory
                                )
                            }
                        }

                        item(span = { GridItemSpan(currentLineSpan = maxLineSpan) }) {
                            SectionHeader(
                                title = if (viewmodel.selectCategory == "All") "All Recipes" else viewmodel.selectCategory,
                                icon = Icons.Default.Menu
                            )
                        }


                        if (viewmodel.recipes.isEmpty()) {
                            item(span = { GridItemSpan(currentLineSpan = maxLineSpan) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 48.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No Recipe Found",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = OrangeColor
                                    )
                                }
                            }
                        } else {
                            items(viewmodel.recipes, key = { it.id }) { recipe ->
                                RecipeCard(recipe, { onRecipeClick(recipe.id) })
                            }
                        }


                    }
                }
            }
        }
    }
}