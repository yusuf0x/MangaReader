package com.manga.mangareader.net.mangaArabic

import androidx.lifecycle.MutableLiveData
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.MangaAr

class ApiClient {
    companion object {
        fun browse(mangas: MutableLiveData<List<MangaAr>>, page: Int, genre: String?) {
            Thread {
                ApiLoader.browse(mangas, page, genre);
            }.start()
        }
        fun search(search: MutableLiveData<List<MangaAr>>, query : String) {
            Thread {
                ApiLoader.search(search, query);
            }.start()
        }
        fun details(detail: MutableLiveData<MangaAr>, manga: MangaAr) {
            Thread {
                ApiLoader.getMoreDetails(detail, manga);
            }.start()
        }
        fun chapters(chapters : MutableLiveData<List<Chapter>>, manga: MangaAr) {
            Thread {
                ApiLoader.getChapters(chapters, manga);
            }.start()
        }
        fun pages(pages : MutableLiveData<List<String>>, chapter: Chapter) {
            Thread {
                ApiLoader.getPages(pages, chapter);
            }.start()
        }
         fun genres(): MutableSet<String> {
            return Constant.getGenres().keys
        }
    }
}