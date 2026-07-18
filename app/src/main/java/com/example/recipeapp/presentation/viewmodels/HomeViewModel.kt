package com.example.recipeapp.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.remote.KtorClient
import com.example.recipeapp.data.remote.RecipeAPiService
import com.example.recipeapp.data.remote.dto.RecipeDTO
import com.example.recipeapp.data.repository.RecipeRepositoryImplementation
import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository: RecipeRepository = RecipeRepositoryImplementation(
        RecipeAPiService(
            KtorClient.client
        )
    )
    var loading by mutableStateOf(false)
        private set

    var errMsg by mutableStateOf("")
        private set

    var recipes by mutableStateOf<List<RecipeDTO>>(emptyList())
        private set


    var selectCategory by mutableStateOf("All")
        private set
    var allCategories by mutableStateOf<List<String>>(listOf("All"))
        private set

    private var allRecipes: List<RecipeDTO> = emptyList()

    fun fetchAllRecipes() {
        loading = true
        errMsg = ""
        try {
            viewModelScope.launch {
                val result = repository.getAllRecipe()
                allRecipes = result
                val cuisines = result.map { it.cuisine }.distinct().sorted()
                allCategories = listOf("All") + cuisines
                applyFilters()
            }
        } catch (e: Throwable) {
            errMsg = e.message ?: "An unexpected error occurred"
        } finally {
            loading = false
        }

    }

    private fun applyFilters() {
        recipes =
            if (selectCategory == "All") allRecipes else allRecipes.filter { it.cuisine == selectCategory }

    }

    fun changeSelectedCategory(category: String) {
        selectCategory = category
        applyFilters()
    }


}