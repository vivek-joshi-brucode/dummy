package com.example.dummy.ui.base

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB


    abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        init()
        listeners()
        observers()
    }

    abstract fun init()
    abstract fun listeners()
    abstract fun observers()
}
