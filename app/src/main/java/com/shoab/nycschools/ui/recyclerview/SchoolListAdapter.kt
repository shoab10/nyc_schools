package com.shoab.nycschools.ui.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoab.nycschools.R
import com.shoab.nycschools.model.NycSchool

class SchoolListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: ArrayList<NycSchool> = ArrayList()
    var onItemClickListener: OnItemClickListener? = null

    /**
     * An interface used to listen for when an item is clicked.
     */
    interface OnItemClickListener {
        fun onItemClicked(view: View, school: NycSchool, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.school_row_item, parent, false)
        return SchoolItemViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
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
        viewHolder.itemView.setOnClickListener {
            onItemClickListener?.onItemClicked(it, school, position)
        }
    }
}