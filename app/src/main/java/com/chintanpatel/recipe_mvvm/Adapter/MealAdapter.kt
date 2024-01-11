package com.chintanpatel.recipe_mvvm.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chintanpatel.recipe_mvvm.Model.Meal
import com.chintanpatel.recipe_mvvm.R

class MealAdapter: RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private var mealList = ArrayList<Meal>()
    var onItemClick: ((Meal) -> Unit)? = null

    fun setMealList(mealList: List<Meal>) {
        this.mealList = mealList as ArrayList<Meal>
        notifyDataSetChanged()
    }

    inner class MealViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mealImage: ImageView = itemView.findViewById(R.id.mealImage)
        val mealName: TextView = itemView.findViewById(R.id.mealName)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(mealList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.meal_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        Glide.with(holder.itemView).load(mealList[position].strMealThumb).into(holder.mealImage)
        holder.mealName.text = mealList[position].strMeal
    }
}