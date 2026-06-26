package com.example.dummy.ui_parallel.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dummy.databinding.FragmentSecondBinding
import com.example.dummy.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : BaseFragment<FragmentSecondBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSecondBinding = FragmentSecondBinding.inflate(layoutInflater)

    override fun FragmentSecondBinding.init() {

    }

    override fun FragmentSecondBinding.listeners() {

    }

    override fun FragmentSecondBinding.observers() {

    }

}