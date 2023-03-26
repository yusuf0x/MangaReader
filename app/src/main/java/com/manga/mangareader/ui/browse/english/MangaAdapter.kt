package com.manga.mangareader.ui.browse.english

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.manga.mangareader.R
import com.manga.mangareader.model.Manga
import com.manga.mangareader.net.mangaEnglish.Constant


class MangaAdapter(private  val mangaList:List<Manga>,private val listener: MangaListener): RecyclerView.Adapter<MangaAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_manga,parent,false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = mangaList[position].title
        Glide
            .with(holder.image)
            .load(Constant.BASE_URL + mangaList[position].art)
            .into(holder.image);

        if(position == mangaList.size - 1) {
            listener.loadMore();
        }
    }

    override fun getItemCount(): Int {
       return  mangaList.size
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        private var manga_art_layout:MaterialCardView
        init {
            image = itemView.findViewById(R.id.manga_art);
            title = itemView.findViewById(R.id.title);
            manga_art_layout = itemView.findViewById(R.id.manga_art_layout)
            manga_art_layout.setOnClickListener {
                listener.click(mangaList[adapterPosition])
            }
        }
    }
}