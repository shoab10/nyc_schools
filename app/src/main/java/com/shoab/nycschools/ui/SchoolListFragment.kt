package com.shoab.nycschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.shoab.nycschools.R
import com.shoab.nycschools.model.NycSchool
import com.shoab.nycschools.ui.recyclerview.SchoolListAdapter
import com.shoab.nycschools.ui.recyclerview.SchoolListAdapter.OnItemClickListener
import com.shoab.nycschools.viewmodel.NycSchoolsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass showing the list of school in nyc.
 */
@AndroidEntryPoint
class SchoolListFragment : Fragment() {
    private val viewModel : NycSchoolsViewModel by viewModels()
    private val adapter = SchoolListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = layoutInflater.inflate(R.layout.fragment_school_list, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.adapter = adapter

        // Adding divider item decorations
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
        recyclerView.addItemDecoration(dividerItemDecoration)

        // Add item click listener
        val onItemClickListener: OnItemClickListener = object : OnItemClickListener {
            override fun onItemClicked(view: View, school: NycSchool, position: Int) {
                val bundle = bundleOf("dbn" to school.dbn)
                view.findNavController()
                    .navigate(R.id.action_SchoolListFragment_to_SatDetailsFragment, bundle)
            }
        }
        adapter.onItemClickListener = onItemClickListener

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
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