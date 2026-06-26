package com.example.dummy.ui_parallel.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dummy.databinding.FragmentForthBinding
import com.example.dummy.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForthFragment : BaseFragment<FragmentForthBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentForthBinding = FragmentForthBinding.inflate(layoutInflater)

    override fun FragmentForthBinding.init() {

    }

    override fun FragmentForthBinding.listeners() {

    }

    override fun FragmentForthBinding.observers() {

    }

}