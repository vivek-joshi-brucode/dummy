package com.example.dummy.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.dummy.ui.dialog.LoadingDialog

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    companion object{
        protected val TAG: String
            get() = this::class.java.simpleName
    }

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private val navController by lazy { findNavController() }
    val loading by lazy { LoadingDialog(requireContext()) }


    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            init()
            listeners()
            observers()
        }
    }

    abstract fun VB.init()
    abstract fun VB.listeners()
    abstract fun VB.observers()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Safe navigation extension to avoid IllegalStateException and double navigation.
     */
    protected fun safeNavigate(directions: NavDirections) {
        val currentDestination = navController.currentDestination

        val action = currentDestination?.getAction(directions.actionId)
        if (action != null && isAdded) {
            navController.navigate(directions)
        }
    }
}

