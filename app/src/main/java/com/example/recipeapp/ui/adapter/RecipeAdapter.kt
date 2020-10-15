package com.example.recipeapp.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.model.Recipe
import com.example.recipeapp.ui.activity.DetailActivity

class RecipeAdapter(
    var recipeList: List<Recipe>
) :
    RecyclerView.Adapter<RecipeAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Recipe?) {
            val tvTitle = view.findViewById<TextView>(R.id.preview)
            val thumbUrl = view.findViewById<ImageView>(R.id.image)

            tvTitle.text = post?.strMeal
            Glide.with(view.context).load(post?.strMealThumb).into(thumbUrl)
            view.setOnClickListener {
                val intent = Intent(view.context, DetailActivity::class.java)
                intent.putExtra("idMeal", post?.idMeal)
                view.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}
