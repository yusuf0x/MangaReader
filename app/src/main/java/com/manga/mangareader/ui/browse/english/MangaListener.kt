package com.manga.mangareader.ui.browse.english

import com.manga.mangareader.model.Manga




interface MangaListener {
    fun click(manga: Manga?)
    fun loadMore()
}