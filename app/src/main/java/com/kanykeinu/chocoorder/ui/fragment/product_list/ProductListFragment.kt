package com.kanykeinu.chocoorder.ui.fragment.product_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

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
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this.requireContext()))
            .get(ProductListViewModel::class.java)
        binding.me = this
        binding.viewModel = viewModel
        setAdapter()
        observeData()
    }

    private fun setAdapter() {
        adapter = ProductsAdapter(
            onIncrementClicked = this::onIncrement,
            onDecrementClicked = this::onDecrement
        )
        binding.rvProductList.adapter = adapter
        binding.rvProductList.addItemDecoration(
            DividerItemDecoration(
                context,
                RecyclerView.VERTICAL
            )
        )
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
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { binding.root.showSnackbar(it) }
        })
        viewModel.products.observe(viewLifecycleOwner, Observer {
            adapter.products = it
        })
        viewModel.orderSaved.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                if (it)
                    navigateToOrderList()
            }
        })
    }

    private fun navigateToOrderList() {
        val orderListDirection =
            ProductListFragmentDirections.actionProductListFragmentToOrderListFragment()
        Navigation.findNavController(binding.root).navigate(orderListDirection)
    }

    fun makeOrder() {
        val totalPrice = binding.totalPrice.text.toString()
        viewModel.saveProducts(adapter.productMap.filterValues { it > 0 }, totalPrice)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.order_history -> {
                navigateToOrderList()
            }
            R.id.logout -> {
                viewModel.clearData()
                navigateToLogin()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToLogin() {
        val direction = ProductListFragmentDirections.actionProductListFragmentToLoginFragment()
        findNavController(binding.root).navigate(direction)
    }
}
