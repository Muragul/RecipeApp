package com.example.recipeapp.ui.adapter.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.model.food.Meal
import com.example.recipeapp.data.model.user.SavedRecipeList
import kotlinx.android.synthetic.main.food_favorites_item.view.*

class FoodRecipeAdapter(val listener: FoodRecipeClickListener) :
    RecyclerView.Adapter<FoodRecipeAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Meal) {
            Glide.with(view.context).load(post.strMealThumb).into(view.image)
            view.setOnClickListener {
                listener.foodRecipeClicked(post)
            }
        }
    }

    interface FoodRecipeClickListener {
        fun foodRecipeClicked(post: Meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.food_favorites_item,
            parent,
            false
        )
        val params = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(SavedRecipeList.foodRecipeList[position])
    }

    override fun getItemCount(): Int {
        return SavedRecipeList.foodRecipeList.size
    }
}