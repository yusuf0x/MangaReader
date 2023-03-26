package com.manga.mangareader.ui.search.english

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manga.mangareader.R
import com.manga.mangareader.model.Manga
import com.manga.mangareader.model.MangaAr
import com.manga.mangareader.net.mangaArabic.MangaArRepository
import com.manga.mangareader.net.mangaEnglish.MangaRepository
import com.manga.mangareader.ui.browse.arabic.MangaArAdapter
import com.manga.mangareader.ui.browse.english.MangaAdapter
import com.manga.mangareader.ui.browse.english.MangaListener
import com.manga.mangareader.ui.detail.DetailActivity
import com.manga.mangareader.ui.detail.DetailArActivity


private const val ARG_PARAM1 = "param1"
class MangaEnSearchFragment : Fragment(),MangaListener {
    private var query: String? = null
    private lateinit var mangaList: MutableList<Manga>
    private lateinit var repository: MangaRepository
    private lateinit var mangaAdapter: MangaAdapter
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(ARG_PARAM1)
        }
        repository = ViewModelProvider(requireActivity())[MangaRepository::class.java]

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View =  inflater.inflate(R.layout.fragment_manga_en_search, container, false)
        mangaList = ArrayList()
        val mangaLayout = view.findViewById<RecyclerView>(R.id.manga_layout)
        mangaLayout.layoutManager = GridLayoutManager(requireContext(), 3)
        mangaAdapter = MangaAdapter( mangaList,this)
        mangaLayout.adapter = mangaAdapter
        progressBar = view.findViewById(R.id.progress_bar)
        repository.search(query!!).observeForever {
            mangaList.clear()
            mangaList.addAll(it)
            mangaAdapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE
        }
        return  view
    }

    companion object {
        @JvmStatic
        fun newInstance(query:String) =
            MangaEnSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, query)
                }
            }
    }

    override fun click(manga: Manga?) {
        val intent : Intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("manga",manga)
        startActivity(intent)
    }

    override fun loadMore() {
    }
}