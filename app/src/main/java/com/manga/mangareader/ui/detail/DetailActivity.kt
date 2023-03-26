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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.manga.mangareader.R
import com.manga.mangareader.db.MangaArViewModel
import com.manga.mangareader.db.MangaEnViewModel
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.Manga
import com.manga.mangareader.net.mangaEnglish.Constant
import com.manga.mangareader.ui.detail.english.ChapterAdapter
import com.manga.mangareader.ui.detail.english.ChapterListener
import com.manga.mangareader.ui.detail.english.MangaDetailsViewModel
import com.manga.mangareader.ui.detail.english.TagAdapter
import com.manga.mangareader.ui.reading.ReadActivity
import java.util.*


class DetailActivity : AppCompatActivity(), ChapterListener {
    private lateinit var image: ImageView
    private lateinit var background_image: ImageView
    private lateinit var title: TextView
    private lateinit var author: TextView
    private lateinit var summary: TextView
    private lateinit var rating: RatingBar
    private lateinit var tagList: RecyclerView
    private lateinit var m: Manga
    private lateinit var repository: MangaDetailsViewModel
    private lateinit var chapterAdapter: ChapterAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var dataViewModel: MangaEnViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        repository = ViewModelProvider(this)[MangaDetailsViewModel::class.java]
        dataViewModel = ViewModelProvider(this)[MangaEnViewModel::class.java]
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
        incomingData()

        findViewById<TextView>(R.id.add_to_lib).setOnClickListener {
            dataViewModel.insert(m)
        }
        findViewById<View>(R.id.all_chapters).setOnClickListener {
            setUpChapterSheet()
        }
    }

    private fun incomingData() {
        val page = intent.getStringExtra("page")
        if (page != null && page == "lib") {
            m = intent.getSerializableExtra("manga") as Manga
            setUpUi(m)
            Log.d("Test","here1")
        } else {
            m = intent.getSerializableExtra("manga") as Manga
            if (m != null) {
                repository.detail(m).observe(this){
                    Log.d("URL",it.summary.toString())
                    Log.d("Test","here2"+"||"+m.url)
                    setUpUi(it)
                }
                repository.chapters(m).observe(this) { chapters -> m.chapters = chapters }
            }
            Log.d("Test","here3 "+m.art)
//            setUpUi(m)
        }
    }
    private  fun setUpUi(manga: Manga){
        Log.d("URL",Constant.BASE_URL + manga.art+"||"+manga.summary)
        Glide.with(this).load(Constant.BASE_URL + manga.art).into(image);
        Glide.with(this).load(Constant.BASE_URL + manga.art).centerCrop().into(background_image);
        title.text = manga.title;
        author.text = String.format(Locale.getDefault(), "%s", manga.author);
        summary?.text = manga.summary;
        if (manga.rating != null && manga.rating!!.isNotEmpty())
        try {
            rating.rating = manga.rating!!.toFloat()
        } catch (e:NumberFormatException) {
            rating.rating = 1F;
        }
        if (manga.tags != null) {
            tagList.adapter = TagAdapter(manga.tags!!);
            Log.d("Tag","not null")
        }
        progressBar.visibility = View.GONE;
    }
    private fun setUpChapterSheet() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.chapter_sheet, null)
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
            chapterAdapter.notifyDataSetChanged()
        }
        chapterAdapter = ChapterAdapter(this, m.chapters!!,this)
        chapterLayout.adapter = chapterAdapter;
        totalChapters.text = String.format(Locale.getDefault(),"Chapters %d",m.chapters!!.size)
        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.show()

    }

    override fun click(chapter: Chapter?) {
        var intent = Intent(this, ReadActivity::class.java)
        intent.putExtra("chapter",chapter)
        startActivity(intent)
    }

}