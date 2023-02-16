package com.shoab.nycschools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoab.nycschools.model.SatData
import com.shoab.nycschools.repository.NycSchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolSatDetailsViewModel @Inject constructor(
    private val repository: NycSchoolsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SatDetailsUiState())
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<SatDetailsUiState> = _uiState

    fun fetchSchoolSatDetails(idn : String) {
        viewModelScope.launch {
            try {
                repository.getStatData(idn)
                    .collect { satData ->
                        _uiState.value = SatDetailsUiState(satData)
                    }
            } catch (e: Exception) {
                _uiState.value = SatDetailsUiState(error = e.message!!)
            }

        }
    }

    // Represents different states for the school list screen
    data class SatDetailsUiState(
        val satData : SatData? = null,
        val error: String = ""
    )
}