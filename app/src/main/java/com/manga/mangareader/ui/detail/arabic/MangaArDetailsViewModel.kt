package com.manga.mangareader.ui.detail.arabic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.MangaAr
import com.manga.mangareader.net.mangaArabic.ApiClient


class MangaArDetailsViewModel : ViewModel() {
    var chapters = MutableLiveData<List<Chapter>>()
    var detail = MutableLiveData<MangaAr>()

    fun chapters(manga: MangaAr): MutableLiveData<List<Chapter>> {
        ApiClient.chapters(chapters, manga)
        return chapters
    }

    fun detail(manga: MangaAr): MutableLiveData<MangaAr> {
        ApiClient.details(detail, manga)
        return detail
    }
}