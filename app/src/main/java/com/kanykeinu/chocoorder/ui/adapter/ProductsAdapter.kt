package com.kanykeinu.chocoorder.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kanykeinu.chocoorder.R
import com.kanykeinu.chocoorder.data.entity.product.Product
import com.kanykeinu.chocoorder.databinding.ItemProductListBinding
import com.kanykeinu.chocoorder.util.loadImage

class ProductsAdapter(
    private val onIncrementClicked: (Int) -> Unit,
    private val onDecrementClicked: (Int) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    var products: List<Product> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val productMap: MutableMap<Product, Int> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_product_list,
            parent,
            false
        )
        return ViewHolder(binding, onIncrementClicked, onDecrementClicked, productMap)
    }

    override fun getItemCount(): Int =
        products.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    class ViewHolder(
        private val binding: ItemProductListBinding,
        private val onIncrementClicked: (Int) -> Unit,
        private val onDecrementClicked: (Int) -> Unit,
        private val productMap: MutableMap<Product, Int>
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            var amount = 0
            with(product) {
                binding.image.loadImage(picture)
                binding.name.text = name
                binding.price.text = price.toString()
                binding.amount.text = if (productMap.containsKey(product))
                    productMap[product].toString()
                else amount.toString()
            }
            binding.btnDecrement.setOnClickListener {
                amount = binding.amount.text.toString().toInt()
                if (amount > 0) {
                    amount--
                    binding.amount.text = amount.toString()
                    onDecrementClicked.invoke(product.price)
                    productMap[product] = amount
                }

            }
            binding.btnIncrement.setOnClickListener {
                amount = binding.amount.text.toString().toInt()
                amount++
                binding.amount.text = amount.toString()
                onIncrementClicked.invoke(product.price)
                productMap[product] = amount
            }
        }
    }
}