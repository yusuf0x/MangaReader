package com.manga.mangareader.net.mangaArabic

import java.lang.String.format
import java.util.*

class ApiBuilder {
    companion object {
        fun buildBrowse(page: Int): String? {
            return format(Locale.getDefault(), Constant.BROWSE_URL, page)
        }

        fun buildCategoryBrowse(page: Int, genre: String?): String? {
            return format(
                Locale.getDefault(),
                Constant.BROWSE_CATEGORY,
                Constant.getGenres()[genre]
            )
        }

        fun buildSearch(query: String?): String? {
            return Constant.BASE_URL_SEARCH + query
        }
    }
}