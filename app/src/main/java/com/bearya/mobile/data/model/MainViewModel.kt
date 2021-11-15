package com.bearya.mobile.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bearya.mobile.data.entity.Teachers
import com.bearya.mobile.data.repository.NewsRepository
import com.bearya.mobile.data.repository.PoetryRepository
import com.bearya.mobile.data.repository.TeacherRepository
import com.bearya.mobile.data.repository.UserRepository
import com.bearya.mobile.ext.hidePhoneNumber
import com.bearya.mobile.ext.setData

class MainViewModel : ViewModel() {

    val username: MutableLiveData<CharSequence?> by lazy { MutableLiveData<CharSequence?>() }
    val avatar: MutableLiveData<String?> by lazy { MutableLiveData<String?>() }
    val phone: MutableLiveData<CharSequence?> by lazy { MutableLiveData<CharSequence?>() }
    val isVip: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    val sentence: LiveData<CharSequence?> by lazy {
        MutableLiveData<CharSequence?>().apply {
            PoetryRepository.queryTodaySentence(viewModelScope) {
                setData(it)
            }
        }
    }
    val teachers: LiveData<MutableList<Teachers>?> by lazy {
        MutableLiveData<MutableList<Teachers>?>().apply {
            TeacherRepository.queryTeachers(viewModelScope) {
                setData(it)
            }
        }
    }
    val story: LiveData<CharSequence?> by lazy {
        MutableLiveData<CharSequence?>().apply {
            NewsRepository.test(viewModelScope) {
                setData(it)
            }
        }
    }

    init {
        invalidate()
    }

    fun invalidate() {
        username.setData(UserRepository.nickname)
        avatar.setData(UserRepository.avatar)
        phone.setData(UserRepository.mobilePhone?.hidePhoneNumber())
        isVip.setData(UserRepository.isVip)
    }

}