package com.example.recipeapp.data.repository

import com.example.recipeapp.data.remote.RecipeAPiService
import com.example.recipeapp.data.remote.dto.AddRecipeRequestDTO
import com.example.recipeapp.data.remote.dto.RecipeDTO
import com.example.recipeapp.data.remote.dto.RecipeResponseDTO
import com.example.recipeapp.domain.repository.RecipeRepository

class RecipeRepositoryImplementation(private val apiService: RecipeAPiService) : RecipeRepository {
    override suspend fun getAllRecipe(): List<RecipeDTO> {
        return apiService.getAllRecipes().recipes
    }

    override suspend fun getRecipeById(id: Int): RecipeDTO {
        return apiService.getRecipeById(id)
    }

    override suspend fun addRecipe(request: AddRecipeRequestDTO) {
        apiService.addRecipe(request)
    }
}