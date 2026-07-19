package com.example.recipeapp.presentation.viewmodels

import android.speech.RecognitionService
import android.util.Log
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

class RecipeDetailViewModel : ViewModel() {
    val repository: RecipeRepository = RecipeRepositoryImplementation(
        RecipeAPiService(
            KtorClient.client
        )
    )


    var isLoading by mutableStateOf(false)
        private set

    var errMsg by mutableStateOf<String?>(null)
        private set

    var recipe by mutableStateOf<RecipeDTO?>(null)
        private set


    fun fetchRecipeDetails(id: Int) {
        isLoading = true
        viewModelScope.launch {
            try {
                val result = repository.getRecipeById(id)
                recipe = result

            } catch (e: Exception) {
                errMsg = e.message
                errMsg?.let { Log.d("ERROR", it) }
            } finally {
                isLoading = false
            }
        }
    }


}