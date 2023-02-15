package com.shoab.nycschools.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoab.nycschools.R
import com.shoab.nycschools.model.NycSchool

class SchoolListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: ArrayList<NycSchool> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.school_row_item, parent, false)
        return SchoolItemViewHolder(itemView)
    }

    fun setItems(newItems: List<NycSchool>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val school = items[position]
        val viewHolder = holder as SchoolItemViewHolder
        viewHolder.title.text = school.schoolName
    }
}