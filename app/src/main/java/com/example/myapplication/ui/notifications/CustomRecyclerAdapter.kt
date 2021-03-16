package com.example.myapplication.ui.notifications

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CustomRecyclerAdapter(private val values: List<String>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    private val LAYOUT_ONE = 0
    private val LAYOUT_TWO = 1
    private val LAYOUT_SiX = 5
    override fun getItemCount() = values.size

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> LAYOUT_ONE
            5 -> LAYOUT_SiX
            else -> LAYOUT_TWO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        if (viewType == LAYOUT_ONE) {
            val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.header_item, parent, false)
            return HeaderViewHolder(itemView)
        }
        if (viewType == LAYOUT_SiX) {
            val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.text_item, parent, false)
            return TextViewHolder(itemView)
        } else {
            val itemView =
                LayoutInflater.from(parent?.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
            return MyViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (holder.getItemViewType() == LAYOUT_ONE) {

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
        var relativeLayoutClcik: RelativeLayout? = null

        init {
            imageView = itemView?.findViewById(R.id.imageView)
            relativeLayoutClcik = itemView?.findViewById(R.id.relativeLayoutClcik)
            imageView?.setOnClickListener {
                Log.e("my", "click image")
            }
        }
    }

    class TextViewHolder(itemView: View) : MyViewHolder(itemView) {

        var textView: TextView? = null

        init {
            textView = itemView?.findViewById(R.id.textView)
            textView?.text = "124"

        }
    }

}