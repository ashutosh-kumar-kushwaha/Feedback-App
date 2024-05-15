package me.ashutoshkk.feedbackapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.ashutoshkk.feedbackapp.data.remote.dto.FeedbackCategory
import me.ashutoshkk.feedbackapp.databinding.FeedbackCategoryItemBinding

class FeedbackCategoryAdapter :
    ListAdapter<FeedbackCategory, FeedbackCategoryAdapter.FeedbackViewHolder>(object :
        ItemCallback<FeedbackCategory>() {
        override fun areItemsTheSame(
            oldItem: FeedbackCategory,
            newItem: FeedbackCategory
        ): Boolean {
            return oldItem.categoryName == newItem.categoryName
        }

        override fun areContentsTheSame(
            oldItem: FeedbackCategory,
            newItem: FeedbackCategory
        ): Boolean {
            return oldItem == newItem
        }

    }) {
    inner class FeedbackViewHolder(private val binding: FeedbackCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feedbackCategory: FeedbackCategory) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        return FeedbackViewHolder(FeedbackCategoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}