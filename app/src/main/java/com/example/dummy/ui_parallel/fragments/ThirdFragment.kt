package com.example.dummy.ui_parallel.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dummy.databinding.FragmentThirdBinding
import com.example.dummy.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : BaseFragment<FragmentThirdBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentThirdBinding = FragmentThirdBinding.inflate(layoutInflater)

    override fun FragmentThirdBinding.init() {

    }

    override fun FragmentThirdBinding.listeners() {

    }

    override fun FragmentThirdBinding.observers() {

    }

}