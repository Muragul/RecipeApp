package com.example.recipeapp.ui.adapter.bar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.BR
import com.example.recipeapp.data.model.bar.BarCategory
import com.example.recipeapp.databinding.BarItemCategoryBinding
import com.example.recipeapp.viewmodel.bar.BarCategoryListViewModel

class BarCategoryAdapter(
    val categoryListViewModel: BarCategoryListViewModel,
    val listener: BarCategoryClickListener
) :
    RecyclerView.Adapter<BarCategoryAdapter.PostViewHolder>() {
    var categoryList: List<BarCategory> = emptyList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val dataBinding = BarItemCategoryBinding.inflate(inflater, p0, false)
        return PostViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        p0.bind(categoryList[p1])
    }

    inner class PostViewHolder(
        private val dataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(post: BarCategory) {
            dataBinding.setVariable(BR.itemData, post)
            dataBinding.executePendingBindings()
            itemView.setOnClickListener {
                listener.categoryItemClicked(post)
            }
        }
    }

    fun updateList(categoryList: List<BarCategory>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    interface BarCategoryClickListener {
        fun categoryItemClicked(task: BarCategory)
    }

}