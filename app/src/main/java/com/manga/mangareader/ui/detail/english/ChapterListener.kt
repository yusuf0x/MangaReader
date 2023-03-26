package com.manga.mangareader.ui.detail.english

import com.manga.mangareader.model.Chapter

interface ChapterListener {
    fun click(chapter: Chapter?)
}