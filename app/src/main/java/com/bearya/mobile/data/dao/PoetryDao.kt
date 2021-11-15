package com.bearya.mobile.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bearya.mobile.data.entity.Poetry
import kotlinx.coroutines.flow.Flow

@Dao
interface PoetryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOne(poetry: Poetry)

    @Query("SELECT * FROM Poetry WHERE date=:date ORDER BY date DESC LIMIT 1")
    fun queryOneByDate(date: String): Flow<Poetry?>
}