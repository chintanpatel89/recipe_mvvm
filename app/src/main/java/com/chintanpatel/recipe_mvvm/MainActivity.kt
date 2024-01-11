package com.chintanpatel.recipe_mvvm

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chintanpatel.recipe_mvvm.Adapter.MealAdapter
import com.chintanpatel.recipe_mvvm.NetworkService.MealInstance
import com.chintanpatel.recipe_mvvm.ViewModel.MealRepository
import com.chintanpatel.recipe_mvvm.ViewModel.MealViewModel
import com.chintanpatel.recipe_mvvm.ViewModel.MealViewModelFactory
import com.chintanpatel.recipe_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mealViewModel: MealViewModel
    lateinit var binding: ActivityMainBinding
    val mealAdapter = MealAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealInstance = MealInstance.getMealInstance()
        val mealRepository = MealRepository(mealInstance)
        mealViewModel = ViewModelProvider(this, MealViewModelFactory(mealRepository)).get(MealViewModel::class.java)

        mealViewModel.mealList.observe(this){
            if(it != null)
                mealAdapter.setMealList(it)
            else
                Toast.makeText(this, "No Recipes Found", Toast.LENGTH_LONG).show()
        }

        mealViewModel.errorMessage.observe(this) {
            Toast.makeText(this, ""+it, Toast.LENGTH_LONG).show()
        }

        binding.mealRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mealAdapter
        }

        binding.btnSearch.setOnClickListener {
            val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            mealViewModel.getMeal(binding.etCountryName.text.toString())
        }

        mealAdapter.onItemClick = {meal ->
            Toast.makeText(this, ""+meal.strMeal, Toast.LENGTH_LONG).show()
        }
    }
}