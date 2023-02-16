package com.shoab.nycschools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoab.nycschools.model.NycSchool
import com.shoab.nycschools.repository.NycSchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NycSchoolsListViewModel @Inject constructor(
    private val repository: NycSchoolsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SchoolListUiState.Success(mutableListOf()))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<SchoolListUiState> = _uiState

    init {
        viewModelScope.launch {
            repository.getNycSchools()
                .collect { schools ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            schools = schools.toMutableList()
                        )
                    }
                }
        }
    }

    // Represents different states for the school list screen
    sealed class SchoolListUiState {
        data class Success(val schools: MutableList<NycSchool>): SchoolListUiState()
        data class Error(val exception: Throwable): SchoolListUiState()
    }
}