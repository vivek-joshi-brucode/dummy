package com.example.dummy.ui.fragments.device

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummy.domain.models.DeviceItemModel
import com.example.dummy.network.models.Object
import com.example.dummy.repository.repo.AppRepositoryImpl
import com.example.dummy.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceFragmentViewModel @Inject constructor(private val repositoryImpl: AppRepositoryImpl) :
    ViewModel() {

        val device: LiveData<ApiState<List<DeviceItemModel>>> = repositoryImpl.getObjects()


}