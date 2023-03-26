package com.manga.mangareader.net.mangaEnglish

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.Manga


class MangaRepository : ViewModel() {
    var mangas = MutableLiveData<List<Manga>>()
    var search = MutableLiveData<List<Manga>>()
    var pages = MutableLiveData<List<String>>()

    fun browse(page: Int, genre: String?): MutableLiveData<List<Manga>> {
        ApiClient.browse(mangas, page, genre)
        return mangas
    }

    fun search(query: String): MutableLiveData<List<Manga>> {
        ApiClient.search(search, query)
        return search
    }
//
    fun pages(chapter: Chapter): MutableLiveData<List<String>> {
        ApiClient.pages(pages, chapter)
        return pages
    }
}