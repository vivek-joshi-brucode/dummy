package com.example.dummy.ui.fragments.dog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummy.network.models.RandomDogPicModel
import com.example.dummy.repository.repo.AppRepositoryImpl
import com.example.dummy.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogFragmentViewModel @Inject constructor(private val repository: AppRepositoryImpl) :
    ViewModel() {

    private var _dogState = MutableLiveData<ApiState<RandomDogPicModel>>(ApiState.Loading)
    var dogState: LiveData<ApiState<RandomDogPicModel>> = _dogState

    init {
        getDog()
    }

    fun getDog() {
        viewModelScope.launch {
            _dogState.value = ApiState.Loading
            _dogState.value = repository.getRandomDogPic()
            _dogState.value = ApiState.Loading
        }
    }

}