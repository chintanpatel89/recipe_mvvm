package com.chintanpatel.recipe_mvvm.ViewModel

import com.chintanpatel.recipe_mvvm.Model.Recipe
import com.chintanpatel.recipe_mvvm.NetworkService.MealInstance
import retrofit2.Response

class MealRepository (private val mealInstance: MealInstance) {
    suspend fun getMeal(countryName: String): Response<Recipe> {
        return mealInstance.getMeal(countryName)
    }
}