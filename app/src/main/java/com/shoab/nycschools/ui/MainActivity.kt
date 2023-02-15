package com.shoab.nycschools.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.shoab.nycschools.R
import com.shoab.nycschools.ui.recyclerview.SchoolListAdapter
import com.shoab.nycschools.viewmodel.NycSchoolsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : NycSchoolsViewModel by viewModels()
    private val adapter = SchoolListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.adapter = adapter

        // Adding divider item decorations
        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
        recyclerView.addItemDecoration(dividerItemDecoration)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is NycSchoolsViewModel.SchoolListUiState.Success -> adapter.setItems(uiState.schools)
                        is NycSchoolsViewModel.SchoolListUiState.Error -> {}
                    }
                }
            }
        }
    }
}