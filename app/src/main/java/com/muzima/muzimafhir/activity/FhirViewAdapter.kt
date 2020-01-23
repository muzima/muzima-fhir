package com.muzima.muzimafhir.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.data.Person
import kotlinx.android.synthetic.main.item_list_entry.view.*

class FhirViewAdapter(private val dataset: MutableList<Person>) :
        RecyclerView.Adapter<FhirViewAdapter.FhirViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class FhirViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): FhirViewAdapter.FhirViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_entry, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return FhirViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: FhirViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val person = dataset[position]
        holder.view.textView_title.text = "Person"
        holder.view.textView_content.text = person.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}