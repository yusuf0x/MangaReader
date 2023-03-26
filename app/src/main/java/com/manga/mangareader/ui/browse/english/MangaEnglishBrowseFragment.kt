package com.manga.mangareader.ui.browse.english

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.manga.mangareader.R
import com.manga.mangareader.model.Manga
import com.manga.mangareader.net.mangaEnglish.ApiClient
import com.manga.mangareader.net.mangaEnglish.MangaRepository
import com.manga.mangareader.ui.detail.DetailActivity


class MangaEnglishBrowseFragment : Fragment(), MangaListener, GenreListener {
    private lateinit var adapter: MangaAdapter
    private val mangaList = mutableListOf<Manga>()
    private lateinit var floatingActionButton : FloatingActionButton
//    private lateinit var sheet: FilterSheet
    private lateinit var repository: MangaRepository
    private var page = 1
    private var genre: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = ViewModelProvider(this).get(MangaRepository::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View =  inflater.inflate(R.layout.fragment_manga_browse, container, false)
        val mangaLayout = view.findViewById<RecyclerView>(R.id.manga_layout)
        mangaLayout.layoutManager = GridLayoutManager(requireContext(),3)
        adapter = MangaAdapter(mangaList,this)
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
            val view = layoutInflater.inflate(R.layout.filter_sheet, null)
            val genreLayout = view.findViewById<RecyclerView>(R.id.genre_list)!!
            genreLayout.setHasFixedSize(true)
            genreLayout.layoutManager = LinearLayoutManager(context)
            genreLayout.adapter = GenreAdapter(ArrayList(ApiClient.genres()), this, genre)
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
        fun newInstance() = MangaEnglishBrowseFragment()
    }

    override fun click(manga: Manga?) {
        val intent : Intent = Intent(requireActivity(),DetailActivity::class.java)
        intent.putExtra("manga",manga)
        startActivity(intent)
    }

    override fun loadMore() {
        repository.browse(page, genre);
    }
    override fun select(genre1: String?) {
        Log.d("GENRE",genre1.toString())
        genre =  genre1;
        page = 1;
        mangaList.clear();
        repository.browse(page, genre);
    }
}