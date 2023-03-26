package com.manga.mangareader.ui.detail


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.manga.mangareader.R
import com.manga.mangareader.db.MangaArDao
import com.manga.mangareader.db.MangaArViewModel
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.MangaAr
import com.manga.mangareader.ui.detail.arabic.ChapterArAdapter
import com.manga.mangareader.ui.detail.arabic.MangaArDetailsViewModel
import com.manga.mangareader.ui.detail.english.ChapterListener
import com.manga.mangareader.ui.detail.english.TagAdapter
import com.manga.mangareader.ui.reading.ReadArActivity
import java.util.*


class DetailArActivity : AppCompatActivity() , ChapterListener {
    private lateinit var image: ImageView
    private lateinit var background_image: ImageView
    private lateinit var title: TextView
    private lateinit var author: TextView
    private lateinit var summary: TextView
    private lateinit var rating: RatingBar
    private lateinit var tagList: RecyclerView
    private lateinit var m: MangaAr
    private lateinit var repository: MangaArDetailsViewModel
    private lateinit var chapterArAdapter: ChapterArAdapter
    private lateinit var progressBar: ProgressBar
//    private lateinit var dao: MangaArDao
    private lateinit var dataViewModel: MangaArViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        repository = ViewModelProvider(this).get(MangaArDetailsViewModel::class.java)
        dataViewModel = ViewModelProvider(this).get(MangaArViewModel::class.java)

        image = findViewById(R.id.manga_image);
        background_image = findViewById(R.id.background_image);
        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        summary = findViewById(R.id.summary);
        rating = findViewById(R.id.rating);
        tagList = findViewById(R.id.tag_list);
        progressBar = findViewById(R.id.progress_bar);
        tagList?.setHasFixedSize(true);
        tagList?.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        parseIncomingData()
        findViewById<TextView>(R.id.add_to_lib).setOnClickListener {
            dataViewModel.insert(m)
        }
        findViewById<View>(R.id.all_chapters).setOnClickListener {
            setUpChapterSheet()
        }

    }

    private fun parseIncomingData() {
        val page = intent.getStringExtra("page")
        if (page != null && page == "lib") {
            m = intent.getSerializableExtra("mangaAr") as MangaAr
            setUpUi(m)
            Log.d("Test","here1")
        } else {
            m = intent.getSerializableExtra("mangaAr") as MangaAr
            if (m != null) {
                Log.d("Test","here2")
                repository.detail(m).observe(this,this::setUpUi)
                repository.chapters(m).observe(this) { chapters -> m.chapters = chapters }
            }
//            setUpUi(m)
        }
    }
    private  fun setUpUi(manga: MangaAr){
        Glide.with(this).load(manga.image).into(image);
        Glide.with(this).load(manga.image).centerCrop().into(background_image);
        title.text = manga.title;
        author.text = String.format(Locale.getDefault(), "%s", manga.author);
        summary?.text = manga.description;
        if (manga.tags != null) {
            tagList.adapter = TagAdapter(manga.tags!!);
            Log.d("Tag","not null")
        }
        progressBar.visibility = View.GONE;

    }
    private fun setUpChapterSheet() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.chapter_sheet1, null)
        val totalChapters = view.findViewById<TextView>(R.id.total_chapters)
        val chapterLayout = view.findViewById<RecyclerView>(R.id.chapter_list)
        val sort = view.findViewById<ImageView>(R.id.sort)
        val close = view.findViewById<ImageView>(R.id.close)
        close.setOnClickListener {
            dialog.dismiss()
        }

        assert(chapterLayout != null)
        assert(sort != null)
        assert(totalChapters != null)

        chapterLayout.setHasFixedSize(true);
        chapterLayout.setItemViewCacheSize(10);
        chapterLayout.layoutManager = LinearLayoutManager(this);

        sort.setOnClickListener { v: View? ->
            Collections.reverse(m.chapters)
            chapterArAdapter.notifyDataSetChanged()
        }
        chapterArAdapter = ChapterArAdapter(this, m.chapters!!,this)
        chapterLayout.adapter = chapterArAdapter;
        totalChapters.text = String.format(Locale.getDefault(),"فصول %d",m.chapters!!.size)
        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.show()

    }

    override fun click(chapter: Chapter?) {
        var intent = Intent(this, ReadArActivity::class.java)
        intent.putExtra("chapterAr",chapter)
        startActivity(intent)
    }

}