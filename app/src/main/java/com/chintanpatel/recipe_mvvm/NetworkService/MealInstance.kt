package com.chintanpatel.recipe_mvvm.NetworkService

import com.chintanpatel.recipe_mvvm.Model.Recipe
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealInstance {

    @GET("filter.php?")
    suspend fun getMeal(@Query("a") countryName: String) : Response<Recipe>

    companion object {
        fun getMealInstance() : MealInstance {
            return Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MealInstance::class.java)
        }
    }
}