package com.manga.mangareader.ui.library.arabic

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manga.mangareader.MTConstants
import com.manga.mangareader.R
import com.manga.mangareader.db.MangaArViewModel
import com.manga.mangareader.db.MangaDb
import com.manga.mangareader.db.MangaEnViewModel
import com.manga.mangareader.model.Manga
import com.manga.mangareader.model.MangaAr
import com.manga.mangareader.ui.detail.DetailActivity
import com.manga.mangareader.ui.detail.DetailArActivity
import com.manga.mangareader.ui.library.english.MangaEnLibraryAdapter
import com.manga.mangareader.ui.library.english.MangaEnglishLibraryFragment
import java.util.*


class MangaArabicLibraryFragment : Fragment() ,MangaArLibraryListener{
    private var mangaList: ArrayList<MangaAr> = ArrayList()
    private lateinit var adapter: MangaArLibraryAdapter
    private lateinit var oops: TextView
    private lateinit var oopsText: TextView
    private lateinit var dataViewModel: MangaArViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_manga_arabic_library, container, false)
        dataViewModel = ViewModelProvider(this)[MangaArViewModel::class.java]
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        oops = view.findViewById(R.id.oops)
        oopsText = view.findViewById(R.id.oops_text)
        val mangaLayout = view.findViewById<RecyclerView>(R.id.manga_layout)
        mangaLayout.layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = MangaArLibraryAdapter( mangaList,this)
        mangaLayout.adapter = adapter
        dataViewModel.allItems.observe(requireActivity(), androidx.lifecycle.Observer {
            if (it.size === 0) {
                oops.text = MTConstants.OOPS[Random().nextInt(MTConstants.OOPS.size)]
                oopsText.setText(R.string.oops_text)
                visibility(View.VISIBLE)
            } else {
                visibility(View.GONE)
            }
            mangaList.clear()
            mangaList.addAll(it)
            adapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE
        })
        return view
    }
    fun visibility(mode: Int) {
        oopsText.visibility = mode
        oops.visibility = mode
    }

    companion object {
        @JvmStatic
        fun newInstance() = MangaEnglishLibraryFragment()
    }

    override fun click(manga: MangaAr?) {
        startActivity(
            Intent(requireActivity(), DetailArActivity::class.java)
                .putExtra("manga", manga)
                .putExtra("page", "lib")
        )
    }
}