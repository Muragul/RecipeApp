package com.example.recipeapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.data.model.Recipe
import com.example.recipeapp.databinding.ItemCategoryBinding
import com.example.recipeapp.ui.fragment.RecipeFragment
import kotlinx.android.synthetic.main.item_category.view.*

class RecipeAdapter() :
    RecyclerView.Adapter<RecipeAdapter.PostViewHolder>() {
    var recipeList: List<Recipe> = emptyList()

    inner class PostViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        val thumbUrl = itemView.image

        fun bind(post: Recipe?) {
            dataBinding.executePendingBindings()
            Glide.with(itemView.context).load(post?.strMealThumb).into(thumbUrl)
            itemView.setOnClickListener {
            }
        }
    }
    fun updateList(recipeList: List<Recipe>) {
        this.recipeList = recipeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ItemCategoryBinding.inflate(inflater, parent, false)
        return PostViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}
