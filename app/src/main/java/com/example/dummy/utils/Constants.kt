package com.example.dummy.utils

import android.Manifest

object Constants {

    const val BASE_URL_DOG = "https://dog.ceo/api/breeds/"
    const val BASE_URL_OBJECTS = "https://api.restful-api.dev/"

    const val REQUEST_CODE_PERMISSIONS = 10
    val  REQUIRED_PERMISSIONS = mutableListOf (
        Manifest.permission.CAMERA
    ).toTypedArray()
}