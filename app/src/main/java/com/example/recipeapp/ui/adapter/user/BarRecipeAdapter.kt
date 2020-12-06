package com.example.recipeapp.ui.adapter.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.model.bar.Drink
import com.example.recipeapp.data.model.user.SavedRecipeList
import kotlinx.android.synthetic.main.food_favorites_item.view.*

class BarRecipeAdapter(val listener: BarRecipeClickListener) :
    RecyclerView.Adapter<BarRecipeAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Drink) {
            Glide.with(view.context).load(post.strDrinkThumb).into(view.image)
            view.setOnClickListener {
                listener.barRecipeClicked(post)
            }
        }
    }

    interface BarRecipeClickListener {
        fun barRecipeClicked(post: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.bar_favorites_item,
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
        holder.bind(SavedRecipeList.barRecipeList[position])

    }

    override fun getItemCount(): Int {
        return SavedRecipeList.barRecipeList.size
    }

}