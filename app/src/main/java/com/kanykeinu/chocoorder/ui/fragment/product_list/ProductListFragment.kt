package com.kanykeinu.chocoorder.ui.fragment.product_list

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

import com.kanykeinu.chocoorder.R
import com.kanykeinu.chocoorder.databinding.ProductListFragmentBinding
import com.kanykeinu.chocoorder.ui.adapter.ProductsAdapter
import com.kanykeinu.chocoorder.ui.fragment.login.ViewModelFactory
import com.kanykeinu.chocoorder.util.showSnackbar

class ProductListFragment : Fragment() {

    private lateinit var viewModel: ProductListViewModel
    private lateinit var binding: ProductListFragmentBinding
    private lateinit var adapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.product_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this.requireContext()))
            .get(ProductListViewModel::class.java)
        binding.me = this
        binding.viewModel = viewModel
        setViews()
        observeData()
    }

    private fun setViews() {
        adapter = ProductsAdapter(
            onIncrementClicked = this::onIncrement,
            onDecrementClicked = this::onDecrement
        )
        binding.rvProductList.adapter = adapter
    }

    private fun onIncrement(price: Int) {
        val currentTotal = binding.totalPrice.text.toString().toInt()
        val newTotal = currentTotal + price
        binding.totalPrice.text = newTotal.toString()
    }

    private fun onDecrement(price: Int) {
        val currentTotal = binding.totalPrice.text.toString().toInt()
        if (currentTotal > 0) {
            val newTotal = currentTotal - price
            binding.totalPrice.text = newTotal.toString()
        }
    }

    private fun observeData() {
        viewModel.error.observe(this, Observer {
            binding.root.showSnackbar(it)
        })
        viewModel.products.observe(this, Observer {
            adapter.products = it
        })
    }

    fun makeOrder() {
        Log.d("Products", adapter.productMap.filterValues { it > 0 }.toString())
        navigateToOrderList()
    }

    private fun navigateToOrderList() {
        val orderListDirection =
            ProductListFragmentDirections.actionProductListFragmentToOrderListFragment()
        Navigation.findNavController(binding.root).navigate(orderListDirection)
    }
}
