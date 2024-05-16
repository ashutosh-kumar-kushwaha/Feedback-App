package me.ashutoshkk.feedbackapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.ashutoshkk.feedbackapp.R
import me.ashutoshkk.feedbackapp.databinding.FeedbackItemBinding
import me.ashutoshkk.feedbackapp.domain.model.Feedback
import me.ashutoshkk.feedbackapp.domain.model.FeedbackItem

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
    inner class FeedbackItemViewHolder(private val binding: FeedbackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feedbackItem: FeedbackItem){
            binding.tvAspect.text = feedbackItem.aspect
            binding.cvNegative.setOnClickListener {
                binding.cvNegative.setCardBackgroundColor(ContextCompat.getColor(it.context, R.color.persian_green))
                binding.cvPositive.setCardBackgroundColor(ContextCompat.getColor(it.context, R.color.gray_extra_light))
                currentList[adapterPosition].selectedFeedback = Feedback.SCOPE_OF_IMPROVEMENT
            }
            binding.cvPositive.setOnClickListener {
                binding.cvPositive.setCardBackgroundColor(ContextCompat.getColor(it.context, R.color.persian_green))
                binding.cvNegative.setCardBackgroundColor(ContextCompat.getColor(it.context, R.color.gray_extra_light))
                currentList[adapterPosition].selectedFeedback = Feedback.DID_WELL
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackItemViewHolder {
        return FeedbackItemViewHolder(FeedbackItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FeedbackItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}