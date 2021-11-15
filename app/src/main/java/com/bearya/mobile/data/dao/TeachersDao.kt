package com.bearya.mobile.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bearya.mobile.data.entity.Teachers
import kotlinx.coroutines.flow.Flow

@Dao
interface TeachersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(teachers: Teachers)

    @Query("SELECT * FROM Teachers LIMIT 3")
    fun queryAllTeachers() : Flow<MutableList<Teachers>?>

}