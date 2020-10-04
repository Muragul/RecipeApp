package com.example.recipeapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CategoryAdapter(
    var list: List<Category>? = null
) : RecyclerView.Adapter<CategoryAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_card, p0, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        p0.bind(list?.get(p1))
    }

    fun clearAll() {
        (list as? ArrayList<Category>)?.clear()
        notifyDataSetChanged()
    }

    inner class PostViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(post: Category?) {
            val tvTitle = view.findViewById<TextView>(R.id.preview)
            val thumbUrl = view.findViewById<ImageView>(R.id.image)

            tvTitle.text = post?.strCategory
            Glide.with(view.context)
                .load(post?.strCategoryThumb)
                .into(thumbUrl)

            view.setOnClickListener{
                val intent = Intent(view.context, RecipeListActivity::class.java)
                intent.putExtra("strCategory", post?.strCategory)
                intent.putExtra("strCategoryDescription", post?.strCategoryDescription)
                view.context.startActivity(intent)
            }

        }
    }

}