package com.example.myapplication.ui.notifications

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CustomRecyclerAdapter(private val values: List<String>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    private val LAYOUT_ONE = 0
    private val LAYOUT_TWO = 1
    override fun getItemCount() = values.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) LAYOUT_ONE else LAYOUT_TWO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return if (viewType == LAYOUT_ONE) {
            val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.header_item, parent, false)
            HeaderViewHolder(itemView)
        } else {
            val itemView =
                LayoutInflater.from(parent?.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
            MyViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (holder.getItemViewType() == LAYOUT_ONE) {
            holder.itemView.setOnClickListener {
                Log.e("my", "click image")
            }
        } else {
            holder.largeTextView?.text = values[position]
            holder.smallTextView?.text = "кот"
            holder.itemView.setOnClickListener {
                Log.e("my", "click " + position)
            }
        }
    }

    open class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var largeTextView: TextView? = null
        var smallTextView: TextView? = null

        init {
            largeTextView = itemView?.findViewById(R.id.textViewLarge)
            smallTextView = itemView?.findViewById(R.id.textViewSmall)
        }
    }

    class HeaderViewHolder(itemView: View) : MyViewHolder(itemView) {

        var imageView: ImageView? = null

        init {
            imageView = itemView?.findViewById(R.id.imageView)
        }
    }

}