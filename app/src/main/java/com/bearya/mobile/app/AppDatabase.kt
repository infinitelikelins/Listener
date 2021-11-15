package com.bearya.mobile.app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bearya.mobile.data.dao.PoetryDao
import com.bearya.mobile.data.dao.TeachersDao
import com.bearya.mobile.data.entity.Poetry
import com.bearya.mobile.data.entity.Teachers

@Database(entities = [
    Poetry::class,
    Teachers::class
], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun poetryDao(): PoetryDao
    abstract fun teachersDao(): TeachersDao

}
