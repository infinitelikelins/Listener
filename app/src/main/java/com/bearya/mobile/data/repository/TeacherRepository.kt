package com.bearya.mobile.data.repository

import com.bearya.mobile.app.AppInit
import com.bearya.mobile.data.entity.Teachers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

object TeacherRepository {

    fun queryTeachers(scope: CoroutineScope, callback: (MutableList<Teachers>?) -> Unit) {
        scope.launch {
            AppInit.database.teachersDao().queryAllTeachers().collect {
                callback.invoke(it)
            }
        }
    }

}