package com.manga.mangareader

import java.util.*

class MTConstants {
    companion object {
        val BASE_URL = "https://www.readm.org/"
        val TAB_ICONS = intArrayOf(
            R.drawable.ic_browse,
            R.drawable.ic_library,
            R.drawable.ic_search,
            R.drawable.ic_setting
        )
        const val DEBUG_TAG = "tain_debug"

        // database constants
        const val MANGA_DB = "manga_db"
        const val MANGA_EN_TABLE = "mangaEn"
        const val MANGA_AR_TABLE = "mangaAr"
        val OOPS = arrayOf(
            "¯\\_(ツ)_/¯",
            "[¬º-°]¬",
            "¯\\_ಠ_ಠ_/¯",
            "¯\\_(ツ)_/¯",
            "ಥ_ಥ",
            "(ø_ø)",
            "＼（〇_ｏ）／"
        )
        const val DATABASE_VERSION = 3
        const val SETTINGS_THEME = "shared_pref_theme"
        const val SETTINGS_THEME_MODE = "shared_pref_theme_mode"
         var ACCENT_LIST = Arrays.asList<Int>(
            R.color.red,
            R.color.pink,
            R.color.purple,
            R.color.deep_purple,
            R.color.red,
            R.color.indigo,
            R.color.blue,
            R.color.light_blue,
            R.color.cyan,
            R.color.teal,
            R.color.green,
            R.color.light_green,
            R.color.lime,
            R.color.yellow,
            R.color.amber,
            R.color.orange,
            R.color.deep_orange,
            R.color.brown,
            R.color.grey,
            R.color.blue_grey,
            R.color.red_300,
            R.color.pink_300,
            R.color.purple_300,
            R.color.deep_purple_300,
            R.color.red_300,
            R.color.indigo_300,
            R.color.blue_300,
            R.color.light_blue_300,
            R.color.cyan_300,
            R.color.teal_300,
            R.color.green_300,
            R.color.light_green_300,
            R.color.lime_300,
            R.color.yellow_300,
            R.color.amber_300,
            R.color.orange_300,
            R.color.deep_orange_300,
            R.color.brown_300,
            R.color.grey_300,
            R.color.blue_grey_300
        )
        const val PACKAGE_NAME = "com.manga.mangareader"
        const val GITHUB_REPO_URL = ""
    }
}