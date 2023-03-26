package com.manga.mangareader.net.mangaEnglish

import java.util.*

class ApiBuilder {
    companion object {
        fun buildBrowse(page: Int): String? {
            return java.lang.String.format(Locale.getDefault(), Constant.BROWSE_URL, page)
        }

        fun buildCategoryBrowse(page: Int, genre: String?): String? {
            return java.lang.String.format(
                Locale.getDefault(),
                Constant.BROWSE_CATEGORY,
                Constant.getGenres()[genre],
                page
            )
        }

        fun buildCombo(url: String?): String? {
            return Constant.BASE_URL + url
        }
    }
}