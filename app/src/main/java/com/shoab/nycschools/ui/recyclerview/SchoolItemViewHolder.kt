package com.shoab.nycschools.ui.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shoab.nycschools.R

class SchoolItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title:  TextView
    init {
        title = itemView.findViewById(R.id.txtView_title)
    }
}

