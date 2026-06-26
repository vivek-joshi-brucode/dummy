package com.example.dummy.ui_parallel.activities

import android.content.Intent
import android.os.Build
import android.view.MenuItem
import android.view.View
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.dummy.R
import com.example.dummy.databinding.ActivityParallelMainBinding
import com.example.dummy.ui.activities.CameraActivity
import com.example.dummy.ui.base.BaseActivity
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParallelMainActivity : BaseActivity<ActivityParallelMainBinding>() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private var pendingDestinationId: Int? = null


    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView


    override fun getViewBinding(): ActivityParallelMainBinding = ActivityParallelMainBinding.inflate(layoutInflater)

    override fun init() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { view, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            view.setPadding(0, topInset, 0, 0)
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.bottomNavigationView) { view, insets ->
            val bottomInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            view.setPadding(0, 0, 0, bottomInset)
            insets
        }

        drawerLayout = binding.drawerLayoutRoot
        navigationView = binding.navigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_parallel) as NavHostFragment
        navController = navHostFragment.navController

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, binding.toolbar, R.string.nav_open,R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }else{
            super.onOptionsItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun listeners() {
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.first_nav_menu -> {
                    pendingDestinationId = R.id.firstFragment
                }
                R.id.second_nav_menu -> {
                    pendingDestinationId = R.id.secondFragment
                }
                R.id.third_nav_menu -> {
                    pendingDestinationId = R.id.thirdFragment
                }
                R.id.forth_nav_menu -> {
                    pendingDestinationId = R.id.forthFragment
                }
            }

            drawerLayout.closeDrawers()
            true
        }

        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerClosed(drawerView: View) {
                pendingDestinationId?.let {
                    navController.navigate(it)
                    pendingDestinationId = null
                }
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerOpened(drawerView: View) {}
            override fun onDrawerStateChanged(newState: Int) {}
        })

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.first_bottom_nav_menu -> navController.navigate(R.id.firstFragment)
                R.id.second_bottom_nav_menu -> navController.navigate(R.id.secondFragment)
                R.id.third_bottom_nav_menu -> navController.navigate(R.id.thirdFragment)
                R.id.forth_bottom_nav_menu ->navController.navigate(R.id.forthFragment)
            }
            true
        }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }


    override fun observers() {

    }

}