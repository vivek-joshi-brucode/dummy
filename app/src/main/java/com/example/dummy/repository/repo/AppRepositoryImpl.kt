package com.example.dummy.repository.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.dummy.domain.mappers.toDeviceDomain
import com.example.dummy.domain.mappers.toDeviceEntity
import com.example.dummy.domain.models.DeviceItemModel
import com.example.dummy.network.apiservice.APIService
import com.example.dummy.repository.AppRepository
import com.example.dummy.repository.baserepo.BaseRepository
import com.example.dummy.network.models.Object
import com.example.dummy.network.models.RandomDogPicModel
import com.example.dummy.room.AppDao
import com.example.dummy.utils.ApiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val appDao: AppDao
) : AppRepository,
    BaseRepository() {

    override suspend fun getRandomDogPic(): ApiState<RandomDogPicModel> {
        return safeApiCall(
            apiCall = { apiService.getRandomDog() }
        )
    }

//    override suspend fun getObjects(): ApiState<List<Object>> {
//        return safeApiCall(
//            apiCall = { apiService.getObjects() }
//        )
//    }

    // Expose LiveData<ApiState<List<Object>>>
    override fun getObjects(): LiveData<ApiState<List<DeviceItemModel>>> {
        val result = MediatorLiveData<ApiState<List<DeviceItemModel>>>()

        // Start with loading state
        result.value = ApiState.Loading

        // Observe cached data from DB
        val source = appDao.getAllDevices()
        result.addSource(source) { cachedObjectsEntity ->
            if (cachedObjectsEntity != null && cachedObjectsEntity.isNotEmpty()) {
                // Map cached entity list to domain objects and emit success
                val domainObjects = cachedObjectsEntity.map { it.toDeviceDomain() }
                result.value = ApiState.Success(domainObjects)
            } else {
                // If cache empty, still loading or error will come from network below
                result.value = ApiState.Loading
            }
        }

        // Fetch remote data in background and update cache
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getObjects()
                if (response.isSuccessful && response.body() != null) {
                    val remoteObjects = response.body()!!
                    // Convert domain objects to entities
                    val entities = remoteObjects.map { it.toDeviceEntity() }
                    appDao.insertDevices(entities)
                } else {
                    withContext(Dispatchers.Main) {
                        // Emit error if no cache
                        if (result.value !is ApiState.Success) {
                            result.value = ApiState.Error("API error: ${response.code()} ${response.message()}")
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Emit error if no cache
                    if (result.value !is ApiState.Success) {
                        result.value = ApiState.Error("Network error: ${e.message}", e)
                    }
                }
            }
        }

        return result
    }

}