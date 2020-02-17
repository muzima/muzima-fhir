package com.muzima.muzimafhir.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muzima.muzimafhir.R
import kotlinx.android.synthetic.main.display_resource_entry.view.*

class ResourceListDisplayAdapter(private val context : Context) :
        RecyclerView.Adapter<ResourceListDisplayAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    private var dataset: MutableList<ResourceListEntry> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.display_resource_entry, parent, false) as View
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.resource_title.text = dataset[position].title

        dataset[position].fields.forEach{ field ->
            val textView = TextView(context)
            textView.text = field
            holder.view.container_fields.addView(textView)
        }
        dataset[position].values.forEach { value ->
            val textView = TextView(context)
            textView.text = value
            holder.view.container_values.addView(textView)
        }
    }
    override fun getItemCount() = dataset.size

    fun setData(data : MutableList<ResourceListEntry>){
        this.dataset = data
        notifyDataSetChanged()
    }
}