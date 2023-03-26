package com.manga.mangareader.ui.detail.english

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.Manga
import com.manga.mangareader.net.mangaEnglish.ApiClient


class MangaDetailsViewModel : ViewModel() {
    var chapters = MutableLiveData<List<Chapter>>()
    var detail = MutableLiveData<Manga>()

    fun chapters(manga: Manga): MutableLiveData<List<Chapter>> {
        ApiClient.chapters(chapters, manga)
        return chapters
    }

    fun detail(manga: Manga): MutableLiveData<Manga> {
        ApiClient.details(detail, manga)
        return detail
    }
}