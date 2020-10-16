package com.example.recipeapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.model.Category

class CategoryAdapter(
    var context: Context,
    var categoryList: List<Category>,
    val listener: CategoryClickListener
) :
    RecyclerView.Adapter<CategoryAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_card, p0, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        p0.bind(categoryList[p1])
    }

    inner class PostViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(post: Category) {
            val tvTitle = view.findViewById<TextView>(R.id.preview)
            val thumbUrl = view.findViewById<ImageView>(R.id.image)
            tvTitle.text = post?.strCategory
            Glide.with(view.context)
                .load(post?.strCategoryThumb)
                .into(thumbUrl)

            view.setOnClickListener {
                listener.categoryItemClicked(post)
            }
        }
    }

    interface CategoryClickListener {
        fun categoryItemClicked(task: Category)
    }

}