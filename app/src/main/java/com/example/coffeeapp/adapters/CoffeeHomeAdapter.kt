package com.example.coffeeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeapp.databinding.CoustemItemBinding
import com.example.coffeeapp.models.coffee.Coffee
import com.example.coffeeapp.ui.fargment.HomeFragment

class CoffeeHomeAdapter : RecyclerView.Adapter<CoffeeHomeAdapter.CoffeeItemViewHolder>() {

    inner class CoffeeItemViewHolder(private val binding: CoustemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Coffee) {
            binding.tvCoffeeName.text = item.name
            binding.tvPrice.text = item.price.toString()
            itemView.apply {
                setOnClickListener {
                    onItemClickListener?.also {
                        it(item)
                    }
                }
                Glide.with(this).load(item.image).into(binding.ivCoffee)
            }


        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Coffee>() {
        override fun areItemsTheSame(oldItem: Coffee, newItem: Coffee): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Coffee, newItem: Coffee): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeItemViewHolder {
        val itemBinding =
            CoustemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CoffeeItemViewHolder, position: Int) {
        val item: Coffee = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount() = differ.currentList.size

    //setOnClickListener
    private var onItemClickListener: ((Coffee) -> Unit)? = null

    fun setOnItemClickListener(listener: (Coffee) -> Unit) {
        onItemClickListener = listener
    }
}