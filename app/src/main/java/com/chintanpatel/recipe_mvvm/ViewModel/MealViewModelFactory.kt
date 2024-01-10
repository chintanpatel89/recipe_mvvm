package com.chintanpatel.recipe_mvvm.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MealViewModelFactory(private val mealRepository: MealRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MealViewModel::class.java)) {
            MealViewModel(this.mealRepository) as T
        } else {
            throw IllegalArgumentException("MealViewModel Not Found")
        }
    }
}