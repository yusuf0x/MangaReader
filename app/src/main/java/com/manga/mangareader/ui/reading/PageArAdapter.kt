package com.manga.mangareader.ui.reading

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.github.piasy.biv.indicator.ProgressIndicator
import com.github.piasy.biv.view.BigImageView
import com.github.piasy.biv.view.GlideImageViewFactory
import com.manga.mangareader.R


class PageArAdapter(var pages:List<String>): RecyclerView.Adapter<PageArAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false);
        return  MyViewHolder(view);
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.page.setImageViewFactory(GlideImageViewFactory())
        holder.page.showImage(Uri.parse(pages[position]));
//        holder.page.
        holder.page.setImageViewFactory(GlideImageViewFactory())

//        holder.page.showImage(Uri.parse(thumbnail), Uri.parse(url));
    }
    override fun getItemCount(): Int {
        return pages.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var page: BigImageView
         val progressBar: ProgressBar
         init {
            page = itemView.findViewById(R.id.page)
            progressBar = itemView.findViewById(R.id.image_progress)
            page.setOptimizeDisplay(false)
            page.setProgressIndicator(object : ProgressIndicator {
                override fun getView(parent: BigImageView?): View ?{
                    return null
                }

                override fun onStart() {
                    progressBar.visibility = View.VISIBLE
                }

                override fun onProgress(progress: Int) {
                }

                override fun onFinish() {
                    progressBar.visibility = View.GONE
                }

            })
        }
    }
}