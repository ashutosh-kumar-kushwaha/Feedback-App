package me.ashutoshkk.feedbackapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import me.ashutoshkk.feedbackapp.R
import me.ashutoshkk.feedbackapp.databinding.OptionItemBinding
import me.ashutoshkk.feedbackapp.domain.model.Option

class OptionAdapter(val list: MutableList<Option>): RecyclerView.Adapter<OptionAdapter.OptionViewHolder>(){
    inner class OptionViewHolder(private val binding: OptionItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(option: Option) {
            binding.tvOption.text = option.text
            if(option.isSelected){
                binding.llOption.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.green_light))
                binding.tvOption.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            }
            else{
                binding.llOption.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.white))
                binding.tvOption.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
            }

            binding.llOption.setOnClickListener {
                list[adapterPosition] = list[adapterPosition].copy(isSelected = !list[adapterPosition].isSelected)
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        return OptionViewHolder(OptionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.bind(list[position])
    }
}