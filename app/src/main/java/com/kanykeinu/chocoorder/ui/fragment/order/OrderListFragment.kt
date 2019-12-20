package com.kanykeinu.chocoorder.ui.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.kanykeinu.chocoorder.R
import com.kanykeinu.chocoorder.data.entity.order.Order
import com.kanykeinu.chocoorder.databinding.OrderListFragmentBinding
import com.kanykeinu.chocoorder.ui.adapter.OrdersAdapter
import com.kanykeinu.chocoorder.ui.fragment.login.ViewModelFactory
import com.kanykeinu.chocoorder.util.showSnackbar
import kotlinx.android.synthetic.main.order_list_fragment.*

class OrderListFragment : Fragment() {

    private lateinit var viewModel: OrderListViewModel
    private lateinit var binding: OrderListFragmentBinding
    private lateinit var adapter: OrdersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this.requireActivity()))
            .get(OrderListViewModel::class.java)
        binding.viewModel = viewModel
        setAdapter()
        observeData()
    }

    private fun setAdapter() {
        adapter = OrdersAdapter(onOrderClicked = { navigateToOrderDetails(it) })
        binding.rvOrdersList.adapter = adapter
        binding.rvOrdersList.addItemDecoration(
            DividerItemDecoration(
                context,
                RecyclerView.VERTICAL
            )
        )
    }

    private fun navigateToOrderDetails(order: Order) {
        val orderDetailsDirection =
            OrderListFragmentDirections.actionOrderListFragmentToDetailedOrderFragment(order)
        findNavController(binding.root).navigate(orderDetailsDirection)
    }

    private fun observeData() {
        viewModel.orders.observe(viewLifecycleOwner, Observer {
            adapter.orders = it
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                binding.root.showSnackbar(it)
            }
        })
    }

}
