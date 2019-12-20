package com.kanykeinu.chocoorder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kanykeinu.chocoorder.R
import com.kanykeinu.chocoorder.data.entity.product.Product
import com.kanykeinu.chocoorder.databinding.ItemOrderProductListBinding
import com.kanykeinu.chocoorder.util.loadImage

class OrderProductsAdapter(
) : RecyclerView.Adapter<OrderProductsAdapter.ViewHolder>() {

    var products: List<Product> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemOrderProductListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_order_product_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        products.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    class ViewHolder(
        private val binding: ItemOrderProductListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(product) {
                binding.name.text = name
                binding.price.text = price.toString()
                binding.image.loadImage(picture)
                binding.quantity.text = quantity.toString()
            }
        }
    }
}