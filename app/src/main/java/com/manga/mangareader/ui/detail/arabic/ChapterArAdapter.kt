package com.manga.mangareader.ui.detail.arabic

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manga.mangareader.R
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.ui.detail.english.ChapterListener


class ChapterArAdapter(var context: Context, var chapters:List<Chapter>,var listener:ChapterListener): RecyclerView.Adapter<ChapterArAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.chapterNo.text = chapters[position].title;
        Log.d("URL_CHAPTER",chapters[position].url.toString())
//        holder.pub.text = chapters[position].publication;
    }

    override fun getItemCount(): Int {
        return chapters.size
    }


     inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var chapterNo: TextView
        var pub: TextView
        init {
            chapterNo = itemView.findViewById(R.id.chapter_no);
            pub = itemView.findViewById(R.id.pub);
            itemView.findViewById<RelativeLayout>(R.id.root_layout).setOnClickListener {
                listener.click(chapters[adapterPosition])
            }
//            listener.click(chapters[adapterPosition])
        }
    }
}