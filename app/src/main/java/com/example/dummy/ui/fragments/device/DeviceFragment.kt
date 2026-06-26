package com.example.dummy.ui.fragments.device

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.dummy.databinding.FragmentDeviceBinding
import com.example.dummy.ui.base.BaseFragment
import com.example.dummy.utils.ApiState
import com.example.dummy.utils.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DeviceFragment : BaseFragment<FragmentDeviceBinding>() {
    private val viewModel by viewModels<DeviceFragmentViewModel>()


    private lateinit var recyclerViewDeviceAdapter: RecyclerViewDeviceAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDeviceBinding  = FragmentDeviceBinding.inflate(layoutInflater)

    override fun FragmentDeviceBinding.init() {
        /**Recycler View initialization*/
        recyclerViewDeviceAdapter = RecyclerViewDeviceAdapter()
        binding.rvDevices.adapter = recyclerViewDeviceAdapter


    }

    override fun FragmentDeviceBinding.listeners() {

    }

    override fun FragmentDeviceBinding.observers() {

        viewModel.device.observe(viewLifecycleOwner){
            when(it){
                is ApiState.Error -> {
                    loading.dismiss()
                    showToast(it.message)
                }
                is ApiState.Loading -> loading.show()
                is ApiState.Success -> {
                    recyclerViewDeviceAdapter.addData(it.data)
                    loading.dismiss()
                }
            }

        }

    }
}