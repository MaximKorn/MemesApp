package com.pack.memesapp.activity.main.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pack.memesapp.R

internal class MemeCellsDataAdapter(private val context: Context,
                                    private val memeCells: List<MemeCell>,
                                    private val onClickListener: CellDataAdapterListener) :
    RecyclerView.Adapter<MemeCellsDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.meme_cell, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = memeCells[position]
        Glide.with(context).load(cell.photoUrl).into(holder.imageView)
        holder.textView.text = cell.title
        holder.cellClickView.setOnClickListener { v ->
            onClickListener.panelClickListener(v, cell)
        }
    }

    override fun getItemCount(): Int {
        return memeCells.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewMeme) as ImageView
        val textView: TextView = view.findViewById(R.id.textViewMeme)
        val cellClickView: View = view.findViewById(R.id.cellView)
    }

    class CellDataAdapterListener(
        val panelClickListener: (v: View, cell: MemeCell) -> Unit
    )
}