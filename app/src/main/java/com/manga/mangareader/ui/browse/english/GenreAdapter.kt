package com.manga.mangareader.ui.browse.english


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manga.mangareader.R


class GenreAdapter (private  val genres:List<String>,  private  val listener: GenreListener,private  var selected:String?): RecyclerView.Adapter<GenreAdapter.MyViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_genre,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val genre = genres[position]
        holder.genre.text = genre
    }

    override fun getItemCount(): Int {
        return  genres.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var genre: TextView
        init {
            genre = itemView.findViewById(R.id.genre)
            var root = itemView.findViewById<RelativeLayout>(R.id.root_layout)
            root.setOnClickListener {
                listener.select(genres[adapterPosition])
            }
        }
    }

}