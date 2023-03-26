package com.manga.mangareader.model

import androidx.annotation.NonNull
import java.io.Serializable

class Chapter :Serializable{
    constructor(title: String?, url: String?, publication: String?, pages: List<String>?) {
        this.title = title
        this.url = url
        this.publication = publication
        this.pages = pages
    }
    @NonNull
    var title: String? = null
    var url: String? = null
    var publication: String? = null
    var pages: List<String>? = null

}