package com.example.recipeapp.data.remote

import com.example.recipeapp.data.remote.dto.AddRecipeRequestDTO
import com.example.recipeapp.data.remote.dto.RecipeDTO
import com.example.recipeapp.data.remote.dto.RecipeResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import okhttp3.Request

class RecipeAPiService(private val client: HttpClient) {
    suspend fun getAllRecipes(): RecipeResponseDTO {
        return client.get(urlString = "${KtorClient.BASE_URL}recipes").body()
    }

    suspend fun getRecipeById(id: Int): RecipeDTO {
        return client.get(urlString = "${KtorClient.BASE_URL}recipes/$id").body()
    }

    suspend fun addRecipe(request: AddRecipeRequestDTO) {
        client.post(urlString = "${KtorClient.BASE_URL}recipes/add") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

}