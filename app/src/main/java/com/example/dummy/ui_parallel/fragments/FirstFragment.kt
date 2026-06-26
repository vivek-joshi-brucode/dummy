package com.example.dummy.ui_parallel.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dummy.databinding.FragmentFirstBinding
import com.example.dummy.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFirstBinding = FragmentFirstBinding.inflate(layoutInflater)

    override fun FragmentFirstBinding.init() {

    }

    override fun FragmentFirstBinding.listeners() {

    }

    override fun FragmentFirstBinding.observers() {

    }

}