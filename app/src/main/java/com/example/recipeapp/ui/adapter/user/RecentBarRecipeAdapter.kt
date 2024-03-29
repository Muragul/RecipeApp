package com.example.recipeapp.ui.adapter.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.model.bar.Drink
import com.example.recipeapp.data.model.user.RecentRecipeList
import kotlinx.android.synthetic.main.recent_bar_item.view.*

class RecentBarRecipeAdapter(val listener: BarRecipeAdapter.BarRecipeClickListener) :
    RecyclerView.Adapter<RecentBarRecipeAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Drink) {
            Glide.with(view.context).load(post.strDrinkThumb).into(view.recent_image)
            view.setOnClickListener {
                listener.barRecipeClicked(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.recent_bar_item,
            parent,
            false
        )
        val params = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(RecentRecipeList.recentBarRecipeList[position])
    }

    override fun getItemCount(): Int {
        return RecentRecipeList.recentBarRecipeList.size
    }
}