package com.manga.mangareader.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manga.mangareader.MTConstants
import java.io.Serializable
@Entity(tableName = MTConstants.MANGA_AR_TABLE)
class MangaAr(
    @PrimaryKey
    var title: String,
    var image: String?,
    var url: String?,
    var views: String?,
    var description: String?,
    var chapter: String?,
    var tags: ArrayList<String>?
):Serializable {
    var author:String ? =null
//    var tags: ArrayList<String>? = null
    var chapters: List<Chapter>? = null
}
//{
//    constructor(
//        title: String?,
//        image: String?,
//        url: String?,
//        views: String?,
//        description: String?,
//        chapter: String?,
//        tags: ArrayList<String>?, )
//    ) {
//        this.title = title
//        this.image = image
//        this.url = url
//        this.views = views
//        this.description = description
//        this.chapter = chapter
//        this.tags = tags
//    }
//    @PrimaryKey
//    @NonNull
//    var title: String?=null
//    var image: String? = null
//    var url: String? = null
//    var views: String? = null
//    var description: String? = null
//    var chapter: String? = null
//    var author:String ? =null
//    var tags: ArrayList<String>? = null
//    var chapters: List<Chapter>? = null
//
//}
/*
* class MangaAr(

        @PrimaryKey(autoGenerate = true) val uid: Int,
        val title: String?,
        val image: String?,
        val url: String?,
        val views: String?,
        val description: String?,
        val chapter: String?,
        val tags: ArrayList<String>?,
    var author:String ? ,
    var chapters: List<Chapter>?
):Serializable*/