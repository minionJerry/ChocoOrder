package com.kanykeinu.chocoorder.ui.fragment.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import com.kanykeinu.chocoorder.R
import com.kanykeinu.chocoorder.databinding.LoginFragmentBinding
import com.kanykeinu.chocoorder.util.showSnackbar

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this.requireContext()))
            .get(LoginViewModel::class.java)
        viewModel.checkUserAuth()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.me = this
        binding.viewModel = viewModel
        observeData()
    }

    fun login() {
        val email = binding.eTextLogin.text.toString()
        val password = binding.eTextPassword.text.toString()
        if (email.isEmpty() or password.isEmpty()) {
            binding.root.showSnackbar("Please fill in all fields")
        } else
            viewModel.login(email, password)
        val imm =
            context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun observeData() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                binding.root.showSnackbar(it)
            }
        })

        viewModel.token.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                navigateToProductList()
            }
        })

        viewModel.isAuthorized.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                if (it)
                    navigateToProductList()
            }
        })
    }

    private fun navigateToProductList() {
        val directionToDataList: NavDirections =
            LoginFragmentDirections.actionLoginFragmentToProductListFragment()
        findNavController(binding.root).navigate(directionToDataList)
    }
}
