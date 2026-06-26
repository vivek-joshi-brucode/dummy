package com.example.dummy.ui.activities

import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.ViewGroupCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.dummy.R
import com.example.dummy.databinding.ActivityMainBinding
import com.example.dummy.ui.base.BaseActivity
import com.example.dummy.ui.fragments.home.HomeFragmentDirections
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private var pendingDestinationId: Int? = null


    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

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
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
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

    override fun listeners() {
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_nav_menu -> {
                    pendingDestinationId = R.id.homeFragment
                }
                R.id.face_detect_nav_menu -> {
                    startActivity(Intent(this,CameraActivity::class.java))
                }
                R.id.devices_nav_menu -> {
                    pendingDestinationId = R.id.deviceFragment
                }
                R.id.dogs_nav_menu -> {
                    pendingDestinationId = R.id.dogFragment
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
                R.id.home_bottom_nav_menu -> navController.navigate(R.id.homeFragment)
                R.id.devices_bottom_nav_menu -> navController.navigate(R.id.deviceFragment)
                R.id.dogs_bottom_nav_menu -> navController.navigate(R.id.dogFragment)
                R.id.face_detect_bottom_nav_menu ->navController.navigate(R.id.privacyFragment)
            }
            true
        }
    }


    override fun observers() {

    }

}