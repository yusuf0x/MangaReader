package com.manga.mangareader.ui.reading

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.piasy.biv.BigImageViewer
import com.github.piasy.biv.loader.glide.GlideImageLoader
import com.manga.mangareader.R
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.net.mangaEnglish.MangaRepository

class ReadActivity : AppCompatActivity() {
    private lateinit var pages: MutableList<String>
    private lateinit var pageAdapter: PageEngAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BigImageViewer.initialize(GlideImageLoader.with(this))
        setContentView(R.layout.activity_read)

        var repository:MangaRepository = ViewModelProvider(this).get(MangaRepository::class.java)
        pages = ArrayList()
        var pageLayout:RecyclerView = findViewById(R.id.pages_layout)
        pageLayout.layoutManager = LinearLayoutManager(this)
        pageAdapter = PageEngAdapter(pages)
        pageLayout.adapter = pageAdapter
        var progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        var chapter:Chapter = intent.getSerializableExtra("chapter") as Chapter
        if (chapter!=null){
            repository.pages(chapter).observeForever {
                pages.addAll(it)
                pageAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }
        }
    }
}