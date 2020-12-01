package com.example.recipeapp.ui.adapter.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.model.food.Meal
import com.example.recipeapp.data.model.user.RecentRecipeList
import kotlinx.android.synthetic.main.recent_food_item.view.*

class RecentRecipeAdapter(val listener: FoodRecipeAdapter.FoodRecipeClickListener) :
    RecyclerView.Adapter<RecentRecipeAdapter.PostViewHolder>() {
    inner class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Meal) {
            Glide.with(view.context).load(post.strMealThumb).into(view.recent_image)
            view.setOnClickListener {
                listener.foodRecipeClicked(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.recent_food_item,
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
        holder.bind(RecentRecipeList.recentFoodRecipeList[position])
    }

    override fun getItemCount(): Int {
        return RecentRecipeList.recentFoodRecipeList.size
    }
}