package com.kanykeinu.chocoorder.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kanykeinu.chocoorder.R
import com.kanykeinu.chocoorder.data.entity.order.Order
import com.kanykeinu.chocoorder.data.entity.product.ProductResponse
import com.kanykeinu.chocoorder.databinding.ItemOrderListBinding
import com.kanykeinu.chocoorder.databinding.ItemProductListBinding
import com.kanykeinu.chocoorder.util.loadImage

class OrdersAdapter(
    private val onOrderClicked: (Order) -> Unit
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    var orders: List<Order> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemOrderListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_order_list,
            parent,
            false
        )
        return ViewHolder(binding, onOrderClicked)
    }

    override fun getItemCount(): Int =
        orders.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    class ViewHolder(
        private val binding: ItemOrderListBinding,
        private val onOrderClicked: (Order) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            with(order) {
                binding.order.text = date
                binding.totalPrice.text = totalPrice
            }
            binding.root.setOnClickListener { onOrderClicked.invoke(order) }
        }
    }
}