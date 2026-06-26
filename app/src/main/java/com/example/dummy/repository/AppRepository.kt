package com.example.dummy.repository

import androidx.lifecycle.LiveData
import com.example.dummy.domain.models.DeviceItemModel
import com.example.dummy.network.models.RandomDogPicModel
import com.example.dummy.utils.ApiState

interface AppRepository {

    suspend fun getRandomDogPic(): ApiState<RandomDogPicModel>

    fun getObjects(): LiveData<ApiState<List<DeviceItemModel>>>


}