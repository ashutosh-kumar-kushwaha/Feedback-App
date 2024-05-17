package me.ashutoshkk.feedbackapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.ashutoshkk.feedbackapp.R
import me.ashutoshkk.feedbackapp.common.Utils.clearItemDecorations
import me.ashutoshkk.feedbackapp.common.Utils.dpToPx
import me.ashutoshkk.feedbackapp.databinding.FeedbackCategoryItemBinding
import me.ashutoshkk.feedbackapp.domain.model.Category
import me.ashutoshkk.feedbackapp.domain.model.Feedback
import me.ashutoshkk.feedbackapp.domain.model.FeedbackCategory

class FeedbackCategoryAdapter(
    val list: MutableList<FeedbackCategory>,
    private val onFeedbackClick: (Feedback, Int, Int) -> Unit
) :
    RecyclerView.Adapter<FeedbackCategoryAdapter.FeedbackViewHolder>() {
    inner class FeedbackViewHolder(private val binding: FeedbackCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var adapter: FeedbackItemsAdapter

        fun bind(feedbackCategory: FeedbackCategory) {
            binding.ivCategoryIcon.setImageResource(
                when (feedbackCategory.category) {
                    Category.CONFIDENCE -> R.drawable.confidence
                    Category.GRAMMAR -> R.drawable.grammar
                    Category.FLUENCY_AND_VOCABULARY -> R.drawable.fluency
                    Category.PRONUNCIATION -> R.drawable.pronunciation
                    Category.OTHER -> R.drawable.others
                    Category.UNSPECIFIED -> R.drawable.others
                }
            )
            binding.llCategory.setOnClickListener {
                if (list[adapterPosition].isOpen) {
                    list[adapterPosition].isOpen = false
                } else {
                    for (i in list.indices) {
                        if (i != adapterPosition && list[i].isOpen) {
                            list[i].isOpen = false
                            notifyItemChanged(i)
                        }
                    }
                    list[adapterPosition].isOpen = true
                }
                notifyItemChanged(adapterPosition)
            }
            if(feedbackCategory.isOpen){
                binding.ivArrow.rotation = 90f
            }
            else{
                binding.ivArrow.rotation = 0f
            }
            if (feedbackCategory.category != Category.OTHER) {
                if (feedbackCategory.isOpen) {
                    binding.rvFeedbackItems.visibility = RecyclerView.VISIBLE
                } else {
                    binding.rvFeedbackItems.visibility = RecyclerView.GONE
                }
                adapter =
                    FeedbackItemsAdapter(feedbackCategory.feedbackItems.toMutableList()) { feedback, position ->
                        list[adapterPosition].feedbackItems[position].selectedFeedback = feedback
                        adapter.list[position] = list[adapterPosition].feedbackItems[position]
                        onFeedbackClick(feedback, adapterPosition, position)
                        val feedbackCount =
                            feedbackCategory.feedbackItems.count { it.selectedFeedback != Feedback.NONE }
                        if (feedbackCount > 0) {
                            binding.tvFeedbackCount.text = feedbackCount.toString()
                            binding.tvFeedbackCount.visibility = View.VISIBLE
                        } else {
                            binding.tvFeedbackCount.visibility = View.GONE
                        }
                    }
                binding.rvFeedbackItems.adapter = adapter
                binding.rvFeedbackItems.layoutManager =
                    GridLayoutManager(binding.root.context, 2, GridLayoutManager.VERTICAL, false)
                binding.rvFeedbackItems.clearItemDecorations()
                binding.rvFeedbackItems.addItemDecoration(
                    FeedbackSpacingItemDecoration(
                        2,
                        binding.root.context.dpToPx(24f),
                        binding.root.context.dpToPx(24f)
                    )
                )
                binding.etFeedback.visibility = View.GONE
            } else {
                binding.rvFeedbackItems.visibility = View.GONE
                if (feedbackCategory.isOpen) {
                    binding.etFeedback.visibility = RecyclerView.VISIBLE
                } else {
                    binding.etFeedback.visibility = RecyclerView.GONE
                }
            }
            binding.tvCategoryTitle.text = feedbackCategory.category.value
            val feedbackCount =
                feedbackCategory.feedbackItems.count { it.selectedFeedback != Feedback.NONE }
            if (feedbackCount > 0) {
                binding.tvFeedbackCount.text = feedbackCount.toString()
                binding.tvFeedbackCount.visibility = View.VISIBLE
            } else {
                binding.tvFeedbackCount.visibility = View.GONE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        return FeedbackViewHolder(
            FeedbackCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        holder.bind(list[position])
    }
}