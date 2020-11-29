package com.example.recipeapp.ui.adapter.bar

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.BR
import com.example.recipeapp.data.model.bar.BarRecipe
import com.example.recipeapp.databinding.BarItemRecipeBinding
import com.example.recipeapp.ui.activity.bar.BarDetailActivity
import kotlinx.android.synthetic.main.bar_item_recipe.view.*

class BarRecipeAdapter :
    RecyclerView.Adapter<BarRecipeAdapter.PostViewHolder>() {
    var recipeList: List<BarRecipe> = emptyList()

    inner class PostViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        val thumbUrl = itemView.image
        fun bind(post: BarRecipe?) {
            dataBinding.setVariable(BR.itemData, post)
            dataBinding.executePendingBindings()
            Glide.with(itemView.context).load(post?.strDrinkThumb).into(thumbUrl)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, BarDetailActivity::class.java)
                intent.putExtra("idDrink", post?.idDrink)
                itemView.context.startActivity(intent)
            }
        }
    }

    fun updateList(recipeList: List<BarRecipe>) {
        this.recipeList = recipeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = BarItemRecipeBinding.inflate(inflater, parent, false)
        return PostViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}