package com.chintanpatel.recipe_mvvm.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chintanpatel.recipe_mvvm.Model.Meal
import com.chintanpatel.recipe_mvvm.Model.Recipe
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealViewModel(private val mealRepository: MealRepository): ViewModel() {
    val mealList = MutableLiveData<List<Meal>>()
    var job: Job? = null
    val errorMessage = MutableLiveData<String>()

    val exceptionHandler = CoroutineExceptionHandler{ _, throwable -> onError("Exception Handled: "+throwable.localizedMessage)}

    fun getMeal(countryName: String) {
        job = CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            val response = mealRepository.getMeal(countryName)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        mealList.value = it.meals
                    }
                } else {
                    onError("Error: "+response.message())
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}