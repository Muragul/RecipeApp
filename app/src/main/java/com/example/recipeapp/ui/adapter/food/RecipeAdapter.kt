package com.example.recipeapp.ui.adapter.food

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.BR
import com.example.recipeapp.data.model.food.Recipe
import com.example.recipeapp.databinding.ItemRecipeBinding
import com.example.recipeapp.ui.activity.food.DetailActivity
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter:
    RecyclerView.Adapter<RecipeAdapter.PostViewHolder>() {
    var recipeList: List<Recipe> = emptyList()

    inner class PostViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        val thumbUrl = itemView.image
        fun bind(post: Recipe?) {
            dataBinding.setVariable(BR.itemData, post)
            dataBinding.executePendingBindings()
            Glide.with(itemView.context).load(post?.strMealThumb).into(thumbUrl)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("idMeal", post?.idMeal)
                itemView.context.startActivity(intent)
                }
        }
    }
    fun updateList(recipeList: List<Recipe>) {
        this.recipeList = recipeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ItemRecipeBinding.inflate(inflater, parent, false)
        return PostViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}
