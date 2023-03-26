package com.manga.mangareader.net.mangaArabic


class Constant {
    companion object {
        var BASE_URL: String = "https://mangaae.com/"
        var BROWSE_URL: String = "https://mangaae.com/manga/page:%d"
        var BROWSE_CATEGORY = "https://mangaae.com/manga/tag:%s%%7Corder:views"
        var CHAPTER_URL = "https://www.readm.org/%s/%d/all-pages"
        var BASE_URL_SEARCH = "https://mangaae.com/manga/search:"
        const val USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201"
        fun getGenres(): HashMap<String, String> {
            val genres: HashMap<String, String> = HashMap()
            genres["أكشن"] = "Action"
            genres["مغامرة"] = "Adventure"
            genres["كوميديا"] = "Comedy"
            genres["دراما"] = "Drama"
            genres["خيال"] = "Fantasy"
            genres["شونين"] = "Shounen"
            genres["فوق الطبيعة"] = "Supernatural"
            genres["فنون قتالية"] = "Martial Arts"
            genres["غموض"] = "Mystery"
            genres["دوجينشي"] = "Doujinshi"
            genres["رعب"] = "Horror"
            genres["ميكانيك"] = "Mecha"
            genres["إطلاقة واحدة"] = "One-Shot"
            genres["نفسي"] = "Psychological"
            genres["رومانسي"] = "Romance"
            genres["حياة مدرسية"] = "School-Life"
            genres["خيال علمي"] = "Sci-fi"
            genres["شوجو"] = "Shoujo"
            genres["جزء من الحياة"] = "Slice-of-Life"
            genres["رياضي"] = "Sports"
            genres["مأساة"] = "Tragedy"
            genres["سينين"] = "Senin"
            genres["تاريخي"] = "Historical"
            genres["ايسيكاي"] = "Isekai"
            genres["جوشي"] = "Josei"
            genres["ايتشي"] = "Ecchi"
            genres["ويب تونز"] = "Webtoons"
            genres["مانهوا كورية"] = "Korean-Manhwa"
            genres["كوميكس"] = "Comics"
            genres["مانجا عربية"] = "Arab-Manga"
            genres["مانوا صينية"] = "Chinese-Manhua"
            genres["حريم"] = "Harem"
            return genres
        }
    }

}