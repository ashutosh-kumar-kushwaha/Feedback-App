package me.ashutoshkk.feedbackapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import me.ashutoshkk.feedbackapp.R
import me.ashutoshkk.feedbackapp.databinding.FeedbackItemBinding
import me.ashutoshkk.feedbackapp.domain.model.Feedback
import me.ashutoshkk.feedbackapp.domain.model.FeedbackItem

class FeedbackItemsAdapter(
    val list: MutableList<FeedbackItem>,
    private val onItemClick: (Feedback, Int) -> Unit
) :
    RecyclerView.Adapter<FeedbackItemsAdapter.FeedbackItemViewHolder>() {
    inner class FeedbackItemViewHolder(private val binding: FeedbackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feedbackItem: FeedbackItem) {
            binding.tvAspect.text = feedbackItem.aspect
            binding.cvNegative.setOnClickListener {
                binding.cvNegative.setCardBackgroundColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.persian_green
                    )
                )
                binding.cvPositive.setCardBackgroundColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.gray_extra_light
                    )
                )
                onItemClick(Feedback.SCOPE_OF_IMPROVEMENT, adapterPosition)
            }
            binding.cvPositive.setOnClickListener {
                binding.cvPositive.setCardBackgroundColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.persian_green
                    )
                )
                binding.cvNegative.setCardBackgroundColor(
                    ContextCompat.getColor(
                        it.context,
                        R.color.gray_extra_light
                    )
                )
                onItemClick(Feedback.DID_WELL, adapterPosition)
            }

            if (feedbackItem.selectedFeedback == Feedback.DID_WELL) {
                binding.cvPositive.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.persian_green
                    )
                )
                binding.cvNegative.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.gray_extra_light
                    )
                )
            }

            if (feedbackItem.selectedFeedback == Feedback.SCOPE_OF_IMPROVEMENT) {
                binding.cvPositive.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.gray_extra_light
                    )
                )
                binding.cvNegative.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.persian_green
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackItemViewHolder {
        return FeedbackItemViewHolder(FeedbackItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeedbackItemViewHolder, position: Int) {
        holder.bind(list[position])
    }
}