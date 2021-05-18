package com.onix.internship.survay.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.onix.internship.survay.database.TestAppDatabase
import com.onix.internship.survay.databinding.LoginFragmentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            TestAppDatabase.getInstance(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.navigationLiveEvent.observe(viewLifecycleOwner, ::navigate)
        viewModel.viewModelScope.launch {
            delay(3000)
            binding.editLogin.setText("Bob")

        }
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}