package com.manga.mangareader.ui.detail.english

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manga.mangareader.R


class TagAdapter( private val tags : List<String>?): RecyclerView.Adapter<TagAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false);
        return  MyViewHolder(view);

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tag.text = tags!![position];
    }

    override fun getItemCount(): Int {
        return  tags!!.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tag: TextView
        init {
            tag = itemView.findViewById(R.id.tag)
        }
    }

}