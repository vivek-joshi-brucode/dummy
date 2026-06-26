package com.example.dummy.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dummy.room.AppDao
import com.example.dummy.room.entity.Device

@Database(entities = [Device::class], version = 1)
abstract class AppDatabase  : RoomDatabase(){
    abstract fun getAppDao() : AppDao
}