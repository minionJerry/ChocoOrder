package com.kanykeinu.chocoorder.ui.fragment.product_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kanykeinu.chocoorder.R

class ProductListFragment : Fragment() {

    companion object {
        fun newInstance() =
            ProductListFragment()
    }

    private lateinit var viewModel: ProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}