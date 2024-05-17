package me.ashutoshkk.feedbackapp.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.ashutoshkk.feedbackapp.R
import me.ashutoshkk.feedbackapp.databinding.FeedbackCategoryItemBinding
import me.ashutoshkk.feedbackapp.domain.model.Category
import me.ashutoshkk.feedbackapp.domain.model.FeedbackCategory

class FeedbackCategoryAdapter(val list: MutableList<FeedbackCategory>) :
    RecyclerView.Adapter<FeedbackCategoryAdapter.FeedbackViewHolder>() {
    inner class FeedbackViewHolder(private val binding: FeedbackCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var adapter: FeedbackItemsAdapter

        fun notify(position: Int) {
            adapter.notifyItemChanged(position)
        }

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
                if(list[adapterPosition].isOpen){
                    list[adapterPosition].isOpen = false
                }
                else{
                    for(i in list.indices){
                        if(i != adapterPosition && list[i].isOpen){
                            list[i].isOpen = false
                            notifyItemChanged(i)
                        }
                    }
                    list[adapterPosition].isOpen = true
                }
                notifyItemChanged(adapterPosition)
//                Log.d("Ashu", "Click")
//                onItemClick(adapterPosition)
            }
            if (feedbackCategory.isOpen) {
                binding.rvFeedbackItems.visibility = RecyclerView.VISIBLE
            } else {
                binding.rvFeedbackItems.visibility = RecyclerView.GONE
            }
            binding.tvCategoryTitle.text = feedbackCategory.category.value
            adapter = FeedbackItemsAdapter(feedbackCategory.feedbackItems.toMutableList()) { feedback, position ->
//                onFeedbackClick(feedback, adapterPosition, position)
                list[adapterPosition].feedbackItems[position].selectedFeedback = feedback
                adapter.list[position] = list[adapterPosition].feedbackItems[position]
                adapter.notifyItemChanged(position)
//                adapter.submitList(list[adapterPosition].feedbackItems)
            }
            binding.rvFeedbackItems.adapter = adapter
            binding.rvFeedbackItems.layoutManager =
                GridLayoutManager(binding.root.context, 2, GridLayoutManager.VERTICAL, false)
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