package com.kanykeinu.chocoorder.ui.fragment.order

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.kanykeinu.chocoorder.R
import com.kanykeinu.chocoorder.data.entity.order.Order
import com.kanykeinu.chocoorder.databinding.DetailedOrderFragmentBinding
import com.kanykeinu.chocoorder.ui.adapter.OrderProductsAdapter
import com.kanykeinu.chocoorder.ui.fragment.login.ViewModelFactory
import com.kanykeinu.chocoorder.util.showSnackbar

class DetailedOrderFragment : Fragment() {

    private lateinit var viewModel: DetailedOrderViewModel
    private lateinit var binding: DetailedOrderFragmentBinding
    private lateinit var arguments: DetailedOrderFragmentArgs
    private lateinit var adapter: OrderProductsAdapter
    private lateinit var order: Order

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.detailed_order_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailedOrderViewModel::class.java)
        arguments = getArguments()?.let { DetailedOrderFragmentArgs.fromBundle(it) }!!
        order = arguments.order
        binding.order = arguments.order
        viewModel.getProductList(order.products)
        setViews()
        observeData()
    }

    private fun setViews() {
        adapter = OrderProductsAdapter()
        binding.rvOrderProducts.adapter = adapter
    }

    private fun observeData(){
        viewModel.products.observe(viewLifecycleOwner, Observer {
            adapter.products = it
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                binding.root.showSnackbar(it)
            }
        })
    }

}
