package com.shoab.nycschools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoab.nycschools.model.NycSchool
import com.shoab.nycschools.repository.NycSchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NycSchoolsViewModel @Inject constructor(
    private val repository: NycSchoolsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SchoolListUiState.Success(emptyList()))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<SchoolListUiState> = _uiState

    init {
        viewModelScope.launch {
            repository.getNycSchools()
                .collect { schools ->
                    _uiState.value = SchoolListUiState.Success(schools)
                }
        }
    }

    // Represents different states for the school list screen
    sealed class SchoolListUiState {
        data class Success(val schools: List<NycSchool>): SchoolListUiState()
        data class Error(val exception: Throwable): SchoolListUiState()
    }
}