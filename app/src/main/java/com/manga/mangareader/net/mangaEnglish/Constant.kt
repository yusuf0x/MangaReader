package com.manga.mangareader.net.mangaEnglish






class Constant {
    companion object {
        var BASE_URL: String = "https://www.readm.org/"
        var BROWSE_URL: String = "https://www.readm.org/popular-manga/%d"
        var BROWSE_CATEGORY = "https://www.readm.org/category/%s/%d"
        var CHAPTER_URL = "https://www.readm.org/%s/%d/all-pages"
        const val USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201"
//        val HEADERS: HashMap<String, String> = object : HashMap<String?, String?>() {
//        }
        fun getGenres(): HashMap<String, String> {
            val genres: HashMap<String, String> = HashMap()
            genres["Action"] = "action"
            genres["Adventure"] = "adventure"
            genres["Comedy"] = "comedy"
            genres["Doujinshi"] = "doujinshi"
            genres["Drama"] = "drama"
            genres["Ecchi"] = "ecchi"
            genres["Fantasy"] = "fantasy"
            genres["Gender Bender"] = "gender-bender"
            genres["Harem"] = "harem"
            genres["Historical"] = "historical"
            genres["Horror"] = "horror"
            genres["Josei"] = "josei"
            genres["Lolicon"] = "lolicon"
            genres["Manga"] = "manga"
            genres["Manhua"] = "manhua"
            genres["Manhwa"] = "manhwa"
            genres["Martial Arts"] = "martial-arts"
            genres["Mecha"] = "mecha"
            genres["Mystery"] = "mystery"
            genres["None"] = "none"
            genres["One shot"] = "one-shot"
            genres["Psychological"] = "psychological"
            genres["Romance"] = "romance"
            genres["School Life"] = "school-life"
            genres["Sci fi"] = "sci-fi"
            genres["Seinen"] = "seinen"
            genres["Shotacon"] = "shotacon"
            genres["Shoujo"] = "shoujo"
            genres["Shoujo Ai"] = "shoujo-ai"
            genres["Shounen"] = "shounen"
            genres["Shounen Ai"] = "shounen-ai"
            genres["Slice of Life"] = "slice-of-life"
            genres["Sports"] = "sports"
            genres["Supernatural"] = "supernatural"
            genres["Tragedy"] = "tragedy"
            genres["Uncategorized"] = "uncategorized"
            genres["Yaoi"] = "yaoi"
            genres["Yuri"] = "yuri"
            return genres
        }
    }

}