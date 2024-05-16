package me.ashutoshkk.feedbackapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.ashutoshkk.feedbackapp.data.remote.dto.FeedbackItem
import me.ashutoshkk.feedbackapp.databinding.FeedbackCategoryItemBinding

class FeedbackItemsAdapter :
    ListAdapter<FeedbackItem, FeedbackItemsAdapter.FeedbackItemViewHolder>(object :
        ItemCallback<FeedbackItem>() {
        override fun areItemsTheSame(oldItem: FeedbackItem, newItem: FeedbackItem): Boolean {
            return oldItem.aspect == newItem.aspect
        }

        override fun areContentsTheSame(oldItem: FeedbackItem, newItem: FeedbackItem): Boolean {
            return oldItem == newItem
        }
    }) {
    inner class FeedbackItemViewHolder(private val binding: FeedbackCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feedbackItem: FeedbackItem){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackItemViewHolder {
        return FeedbackItemViewHolder(FeedbackCategoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FeedbackItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}