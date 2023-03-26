package com.manga.mangareader.ui.browse.arabic


import com.manga.mangareader.model.MangaAr


interface MangaArListener {
    fun click(manga: MangaAr?)
    fun loadMore()
}