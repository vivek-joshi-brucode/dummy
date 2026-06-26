package com.example.dummy.domain.mappers

import com.example.dummy.domain.models.DeviceItemModel
import com.example.dummy.network.models.Object
import com.example.dummy.room.entity.Device


fun Object.toDeviceEntity() : Device{
    return Device(
        id = id,
        name = name
    )
}

fun Device.toDeviceDomain() = DeviceItemModel(
    id = id,
    name = name
)