package com.muzima.muzimafhir.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.data.Observation
import kotlinx.android.synthetic.main.item_list_entry.view.*

class ObservationViewAdapter(private val dataset: MutableList<Observation>) :
        RecyclerView.Adapter<ObservationViewAdapter.ObservationViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ObservationViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ObservationViewAdapter.ObservationViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_entry, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return ObservationViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ObservationViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val observation = dataset[position]
        holder.view.textView_title.text = "Observation"
        holder.view.textView_content.text = observation.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}