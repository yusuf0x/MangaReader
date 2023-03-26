package com.manga.mangareader.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manga.mangareader.MTConstants
import java.io.Serializable
@Entity(tableName = MTConstants.MANGA_EN_TABLE)
class Manga
//    constructor
        (
        @PrimaryKey
//        @PrimaryKey(autoGenerate = true) val id: Int,
        var title: String,
        var url: String?,
        var summary: String?,
        var rating: String?,
        var art: String?,
        var tags: ArrayList<String>?,
    ) : Serializable {
//        this.title = title
//        this.art = art
//        this.url = url
//        this.rating = rating
//        this.summary = summary
//        this.tags = tags
//    }
//    @PrimaryKey
//    @NonNull
//    var id:Int = 0
//    var title: String?=null
//    var art: String? = null
//    var url: String? = null
//    var rating: String? = null
//    var summary: String? = null

//    @PrimaryKey(autoGenerate = true) val id:Int=-1;

    var chapter: String? = null

    var status: String? = null
    var author: String? = null
    var authorUrl: String? = null
//    var tags: ArrayList<String>? = null
    var chapters: List<Chapter>? = null
}