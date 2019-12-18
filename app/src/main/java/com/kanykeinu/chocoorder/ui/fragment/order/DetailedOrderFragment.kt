package com.kanykeinu.chocoorder.ui.fragment.order

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kanykeinu.chocoorder.R

class DetailedOrderFragment : Fragment() {

    companion object {
        fun newInstance() = DetailedOrderFragment()
    }

    private lateinit var viewModel: DetailedOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detailed_order_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailedOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
