package com.example.dummy.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.example.dummy.repository.repo.AppRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val repository: AppRepositoryImpl) :
    ViewModel() {


}