package com.manga.mangareader.ui.settings

import android.app.Activity
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.manga.mangareader.MTConstants
import com.manga.mangareader.MTPreferences.getTheme
import com.manga.mangareader.MTPreferences.storeTheme
import com.manga.mangareader.R
import com.manga.mangareader.ThemeHelper

class AccentAdapter(activity: Activity) : RecyclerView.Adapter<AccentAdapter.MyViewHolder>() {
    private val activity: Activity
    var accentList: List<Int>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_accent, parent, false)
        return MyViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        ImageViewCompat.setImageTintList(holder.accent, ColorStateList.valueOf(ContextCompat.getColor(activity,accentList[position])))
        if (accentList[position] == getTheme(activity.applicationContext)) holder.check.visibility =
            View.VISIBLE else holder.check.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return accentList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accent: ImageButton
         val check: ImageButton

        init {
            accent = itemView.findViewById(R.id.accent)
            check = itemView.findViewById(R.id.check)
            accent.setOnClickListener { v: View? ->
                storeTheme(
                    activity.applicationContext,
                    accentList[adapterPosition]
                )
                ThemeHelper.applySettings(activity)
            }
        }
    }

    init {
        accentList = MTConstants.ACCENT_LIST
        this.activity = activity
    }
}