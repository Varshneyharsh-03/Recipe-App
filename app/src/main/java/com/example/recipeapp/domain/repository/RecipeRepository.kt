package com.example.recipeapp.domain.repository

import com.example.recipeapp.data.remote.dto.AddRecipeRequestDTO
import com.example.recipeapp.data.remote.dto.RecipeDTO
import com.example.recipeapp.data.remote.dto.RecipeResponseDTO

interface RecipeRepository {
    suspend fun getAllRecipe() : List<RecipeDTO>
    suspend fun getRecipeById(id : Int) : RecipeDTO
    suspend fun addRecipe(request : AddRecipeRequestDTO )

}