package com.shoab.nycschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.shoab.nycschools.R
import com.shoab.nycschools.viewmodel.SchoolSatDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass showing the sat details in nyc.
 */
@AndroidEntryPoint
class SatDetailsFragment : Fragment() {
    private val viewModel: SchoolSatDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Getting the DBN from the argument
        val dbn = arguments?.getString("dbn")
        dbn?.let {
            viewModel.fetchSchoolSatDetails(dbn)
        }
        return layoutInflater.inflate(R.layout.fragment_sat_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Update the text view with school data
        val textView = view.findViewById<TextView>(R.id.details_textview)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    uiState.satData?.let {
                        val text = "DBN : " + it.dbn + "\n" +
                                "School name : " + it.schoolName + "\n" +
                                "Test takers : " + it.testTakersNumber + "\n" +
                                "Reading Avg score : " + it.readingAvg + "\n" +
                                "Math Avg score : " + it.mathAvg + "\n" +
                                "Writing Avg score : " + it.writingAvg
                                textView.text = text
                    }
                }
            }
        }
    }
}