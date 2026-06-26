package com.example.dummy.network.apiservice

import com.example.dummy.network.models.Object
import com.example.dummy.network.models.RandomDogPicModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getRandomDog(@Url fullUrl  : String = "https://dog.ceo/api/breeds/image/random"): Response<RandomDogPicModel>

    @GET
    suspend fun getObjects(@Url fullUrl : String = "https://api.restful-api.dev/objects"): Response<List<Object>>

}