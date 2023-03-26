package com.manga.mangareader.net.mangaArabic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.MangaAr


class MangaArRepository : ViewModel() {
    var mangas = MutableLiveData<List<MangaAr>>()
    var search = MutableLiveData<List<MangaAr>>()
    var pages = MutableLiveData<List<String>>()

    fun browse(page: Int, genre: String?): MutableLiveData<List<MangaAr>> {
        ApiClient.browse(mangas, page, genre)
        return mangas
    }

    fun search(query: String): MutableLiveData<List<MangaAr>> {
        ApiClient.search(search, query)
        return search
    }
//
    fun pages(chapter: Chapter): MutableLiveData<List<String>> {
        ApiClient.pages(pages, chapter)
        return pages
    }
}