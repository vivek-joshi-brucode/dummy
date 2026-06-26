package com.example.dummy.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dummy.R
import com.example.dummy.databinding.ActivityFlowOptionsBinding
import com.example.dummy.databinding.ActivityMainBinding
import com.example.dummy.ui.base.BaseActivity
import com.example.dummy.ui_parallel.activities.ParallelMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowOptionsActivity : BaseActivity<ActivityFlowOptionsBinding>(){

    override fun getViewBinding(): ActivityFlowOptionsBinding  = ActivityFlowOptionsBinding.inflate(layoutInflater)

    override fun init() {

    }

    override fun listeners() {
        binding.optionOne.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.optionTwo.setOnClickListener {
            startActivity(Intent(this, ParallelMainActivity::class.java))
        }
    }

    override fun observers() {

    }

}