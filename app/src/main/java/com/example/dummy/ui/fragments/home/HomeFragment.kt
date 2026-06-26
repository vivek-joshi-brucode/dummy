package com.example.dummy.ui.fragments.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.dummy.R
import com.example.dummy.databinding.FragmentHomeBinding
import com.example.dummy.ui.base.BaseFragment
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeFragmentViewModel by viewModels<HomeFragmentViewModel>()

    private val submitText = "Hold to Submit"


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun FragmentHomeBinding.observers() {


    }

    override fun FragmentHomeBinding.listeners() {
        btnProgress.setOnProgressCompleteListener {
//            showColorPicker()
            val action = HomeFragmentDirections.actionHomeFragmentToDeviceFragment()
            safeNavigate(action)
        }
    }

    override fun FragmentHomeBinding.init() {
        btnProgress.apply {
            text = submitText
            setCornerRadius(24f)
            setStrokeColor(ContextCompat.getColor(context, R.color.black))
            setStrokeWidth(2f)
            setBackgroundColorCustom(ContextCompat.getColor(context, R.color.white))
            setProgressColor(Color.GRAY)
        }
    }

    private fun showColorPicker() {
        ColorPickerDialog
            .Builder(requireContext())        				// Pass Activity Instance
            .setTitle("Pick Theme")           	// Default "Choose Color"
            .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
            .setDefaultColor(R.color.sunset)     // Pass Default Color
            .setColorListener { color, colorHex ->
//                binding.borderView.setGradientColors(intArrayOf(Color.RED, Color.YELLOW, Color.CYAN))
//                binding.borderView.setBorderColor(color)
            }
            .show()
    }

}