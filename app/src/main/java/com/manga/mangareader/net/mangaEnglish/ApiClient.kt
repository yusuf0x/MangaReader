package com.manga.mangareader.net.mangaEnglish

import androidx.lifecycle.MutableLiveData
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.Manga

class ApiClient {
    companion object {
        fun browse(mangas: MutableLiveData<List<Manga>>, page: Int, genre: String?) {
            Thread {
                ApiLoader.browse(mangas, page, genre);
            }.start()
        }
        fun search(mangas: MutableLiveData<List<Manga>>, query: String) {
            Thread {
                ApiLoader.search(mangas, query);
            }.start()
        }
        fun details(detail: MutableLiveData<Manga>, manga: Manga) {
            Thread {
                ApiLoader.getMoreDetails(detail, manga);
            }.start()
        }
        fun chapters(chapters : MutableLiveData<List<Chapter>>, manga: Manga) {
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