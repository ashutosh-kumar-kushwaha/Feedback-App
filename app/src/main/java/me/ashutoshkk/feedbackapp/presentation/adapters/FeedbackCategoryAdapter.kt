package me.ashutoshkk.feedbackapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.ashutoshkk.feedbackapp.R
import me.ashutoshkk.feedbackapp.databinding.FeedbackCategoryItemBinding
import me.ashutoshkk.feedbackapp.domain.model.Category
import me.ashutoshkk.feedbackapp.domain.model.FeedbackCategory

class FeedbackCategoryAdapter :
    ListAdapter<FeedbackCategory, FeedbackCategoryAdapter.FeedbackViewHolder>(object :
        ItemCallback<FeedbackCategory>() {
        override fun areItemsTheSame(
            oldItem: FeedbackCategory,
            newItem: FeedbackCategory
        ): Boolean {
            return oldItem.category == newItem.category
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
            binding.ivCategoryIcon.setImageResource(
                when (feedbackCategory.category) {
                    Category.CONFIDENCE -> R.drawable.confidence
                    Category.GRAMMAR -> R.drawable.grammar
                    Category.FLUENCY_AND_VOCABULARY -> R.drawable.fluency
                    Category.PRONUNCIATION -> R.drawable.pronunciation
                    Category.OTHER -> R.drawable.pronunciation
                }
            )
            binding.llCategory.setOnClickListener {
                if(currentList[adapterPosition].isOpen){
                    currentList[adapterPosition].isOpen = false
                }
                else{
                    for(i in currentList.indices){
                        if(i != adapterPosition){
                            currentList[i].isOpen = false
                        }
                    }
                    currentList[adapterPosition].isOpen = true
                }
            }
            binding.tvCategoryTitle.text = feedbackCategory.category.name
            val adapter = FeedbackItemsAdapter()
            binding.rvFeedbackItems.adapter = adapter
            binding.rvFeedbackItems.layoutManager = GridLayoutManager(binding.root.context, 2, GridLayoutManager.VERTICAL, false)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        return FeedbackViewHolder(FeedbackCategoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}