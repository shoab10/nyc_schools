package com.shoab.nycschools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoab.nycschools.repository.NycSchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NycSchoolsViewModel @Inject constructor(
    private val repository: NycSchoolsRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getNycSchools()
        }
    }

    fun getSchools() {
        viewModelScope.launch {
            repository.getNycSchools().collect()
        }
    }
}