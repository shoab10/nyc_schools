package com.shoab.nycschools

import com.shoab.nycschools.model.NycSchool
import com.shoab.nycschools.model.SatData
import com.shoab.nycschools.repository.NycSchoolsRepository
import com.shoab.nycschools.viewmodel.NycSchoolsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolListViewModelTest {

    @Before
    fun prepareTest() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun cleanupTest() {
        Dispatchers.resetMain()
    }

    @Test
    fun uiState_initiallyLoadingEmpty() = runTest {
        val viewModel = NycSchoolsListViewModel(FakeMyModelRepository())
        assertEquals(viewModel.uiState.first(),
            NycSchoolsListViewModel.SchoolListUiState.Success(mutableListOf())
        )
    }

    @Test
    fun uiState_initiallyLoadingHasSchools() = runTest {
        val repository = FakeMyModelRepository()
        // Providing fake data to repository
        val schools = mutableListOf(NycSchool("1", "MySchool"))
        repository.schools.addAll(schools)

        val viewModel = NycSchoolsListViewModel(repository)

        // Testing the second emission since the first will be empty on creation
        assertEquals(viewModel.uiState.drop(1).first(),
            NycSchoolsListViewModel.SchoolListUiState.Success(schools)
        )
    }
}

private class FakeMyModelRepository : NycSchoolsRepository {
    val schools = mutableListOf<NycSchool>()

    override suspend fun getNycSchools(): Flow<List<NycSchool>> {
        return flowOf(schools)
    }

    override suspend fun getStatData(idn: String): Flow<SatData> {
        TODO("Not testing this method")
    }
}