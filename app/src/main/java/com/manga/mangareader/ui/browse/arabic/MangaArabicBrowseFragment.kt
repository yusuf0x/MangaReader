package com.manga.mangareader.ui.browse.arabic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.manga.mangareader.R
import com.manga.mangareader.model.MangaAr
import com.manga.mangareader.net.mangaArabic.ApiClient
import com.manga.mangareader.net.mangaArabic.MangaArRepository
import com.manga.mangareader.ui.detail.DetailArActivity

class MangaArabicBrowseFragment : Fragment() , MangaArListener,GenreArListener {
    private lateinit var adapter: MangaArAdapter
    private val mangaList = mutableListOf<MangaAr>()
    private lateinit var floatingActionButton : FloatingActionButton
    private lateinit var repository: MangaArRepository
    private var page = 1
    private var genre: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = ViewModelProvider(this).get(MangaArRepository::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View =  inflater.inflate(R.layout.fragment_manga_browse, container, false)
        val mangaLayout = view.findViewById<RecyclerView>(R.id.manga_layout)
        mangaLayout.layoutManager = GridLayoutManager(requireContext(),3)
        adapter = MangaArAdapter(mangaList,this)
        mangaLayout.adapter = adapter
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.visibility = View.GONE
        repository.browse(page,genre).observe(requireActivity(), Observer {
            mangaList.addAll(it)
            page += 1;
            adapter.notifyDataSetChanged();
        })
        floatingActionButton = view.findViewById(R.id.filter)
        floatingActionButton.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.filter_sheet1, null)
            val genreLayout = view.findViewById<RecyclerView>(R.id.genre_list)!!
            genreLayout.setHasFixedSize(true)
            genreLayout.layoutManager = LinearLayoutManager(context)
            genreLayout.adapter = GenreArAdapter(ArrayList(ApiClient.genres()), this, genre)
            val reset = view.findViewById<MaterialButton>(R.id.reset_btn)!!
            reset.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()
        }
        return  view
    }
    companion object {
        @JvmStatic
        fun newInstance() = MangaArabicBrowseFragment()
    }
    override fun click(manga: MangaAr?) {
        val intent : Intent = Intent(requireActivity(), DetailArActivity::class.java)
        intent.putExtra("mangaAr",manga)
        startActivity(intent)
    }
    override fun loadMore() {
        repository.browse(page, genre);
    }
    override fun select(genre1: String?) {
        Log.d("GENREAr",genre1.toString())
        genre =  genre1;
        page = 1;
        mangaList.clear();
        repository.browse(page, genre);
    }
}