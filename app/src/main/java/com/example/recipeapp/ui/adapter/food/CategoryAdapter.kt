package com.example.recipeapp.ui.adapter.food

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.BR
import com.example.recipeapp.data.model.food.Category
import com.example.recipeapp.databinding.ItemCategoryBinding
import com.example.recipeapp.viewmodel.food.FoodCategoryListViewModel
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(
    val categoryListViewModel: FoodCategoryListViewModel,
    val listener: CategoryClickListener
) :
    RecyclerView.Adapter<CategoryAdapter.PostViewHolder>() {
    var categoryList: List<Category> = emptyList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val dataBinding = ItemCategoryBinding.inflate(inflater, p0, false)
        return PostViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        p0.bind(categoryList[p1])
    }

    inner class PostViewHolder(
        private val dataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {
        val thumbUrl = itemView.image

        fun bind(post: Category) {
            dataBinding.setVariable(BR.itemData, post)
            dataBinding.executePendingBindings()
            itemView.setOnClickListener {
                listener.categoryItemClicked(post)
            }
            Glide.with(itemView.context)
                .load(post.strCategoryThumb)
                .into(thumbUrl)
        }
    }

    fun updateList(categoryList: List<Category>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    interface CategoryClickListener {
        fun categoryItemClicked(task: Category)
    }

}