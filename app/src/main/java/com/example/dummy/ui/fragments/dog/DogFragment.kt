package com.example.dummy.ui.fragments.dog

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.dummy.R
import com.example.dummy.databinding.FragmentDogBinding
import com.example.dummy.network.models.RandomDogPicModel
import com.example.dummy.ui.base.BaseFragment
import com.example.dummy.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogFragment : BaseFragment<FragmentDogBinding>() {
    private val viewModel: DogFragmentViewModel by viewModels<DogFragmentViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDogBinding = FragmentDogBinding.inflate(layoutInflater)

    override fun FragmentDogBinding.observers() {
        viewModel.dogState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ApiState.Loading -> loading.apply { if (isShowing) dismiss() else show() }
                is ApiState.Success -> showDog(state.data)
                is ApiState.Error -> showError(state.message)
            }

        }
    }

    override fun FragmentDogBinding.listeners() {
        binding.ivRefresh.setOnClickListener {
            viewModel.getDog()
        }
    }

    override fun FragmentDogBinding.init() {

    }


    private fun showDog(randomDogPicModel: RandomDogPicModel) {
        Glide
            .with(binding.ivDog)
            .load(randomDogPicModel.message)
            .placeholder(R.drawable.img_placeholder)
            .into(binding.ivDog)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}