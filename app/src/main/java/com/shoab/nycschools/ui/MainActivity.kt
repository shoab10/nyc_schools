package com.shoab.nycschools.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shoab.nycschools.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple Activity that hosts the different fragments and navigation
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}