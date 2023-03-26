package com.manga.mangareader.net.mangaArabic


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.MangaAr
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException


class ApiLoader {
    companion object{
        fun browse(mutableManga: MutableLiveData<List<MangaAr>>, page:Int, genre:String?){
            try {
                val mangaList: MutableList<MangaAr> = ArrayList()
                Log.d("Genre",genre.toString())
                val pageUrl: String = if (genre == null) ApiBuilder.buildBrowse(page).toString() else ApiBuilder.buildCategoryBrowse(page, genre).toString()
                Log.d("URL",pageUrl)
                val doc: Element = Jsoup.connect(pageUrl).userAgent(Constant.USER_AGENT).get().body()
                for (pg in doc.select("div[class=mangacontainer]")) {
                    var english = pg.select("a[class=manga]").first()!!.text()
                    var arabic = pg.select("a[class=manga]").last()!!.text();
                    var pub = pg.select("div[class=year]").text();
                    var image = pg.select("a>img").attr("src");
                    var link = pg.select("a[class=manga]").attr("href");
                    var views = pg.select("div[class=details]").text();
                    val m = MangaAr(arabic, image, link, views, null, null,null)
//                    val m = MangaAr();

                    mangaList.add(m)
                }
                mutableManga.postValue(mangaList)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        fun search(search: MutableLiveData<List<MangaAr>>,query:String){
            try {
                val mangaList: MutableList<MangaAr> = ArrayList()
                val pageUrl: String =  ApiBuilder.buildSearch(query).toString()
                Log.d("URL",pageUrl)
                val doc: Element = Jsoup.connect(pageUrl).userAgent(Constant.USER_AGENT).get().body()
                for (pg in doc.select("div[class=mangacontainer]")) {
                    var english = pg.select("a[class=manga]").first()!!.text()
                    var arabic = pg.select("a[class=manga]").last()!!.text();
                    var pub = pg.select("div[class=year]").text();
                    var image = pg.select("a>img").attr("src");
                    var link = pg.select("a[class=manga]").attr("href");
                    var views = pg.select("div[class=details]").text();
                    val m = MangaAr(arabic, image, link, views, null, null,null)
                    mangaList.add(m)
                }
                search.postValue(mangaList)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        fun getChapters(chapters: MutableLiveData<List<Chapter>>, manga:MangaAr){
            try {
                var chapterList: MutableList<Chapter> = ArrayList()
                val doc = Jsoup.connect(manga.url).get().body().select("ul[class=new-manga-chapters]>li")
                for (chp in doc) {
                    var title = chp.select("a").text()
                    var url = chp.select("a").attr("href")
                    chapterList.add(Chapter(title, url, null, null))
                }
                manga.chapters = chapterList;
                chapters.postValue(chapterList);
            }catch (e:IOException){
                e.printStackTrace()
            }
        }
        fun getPages(data: MutableLiveData<List<String>>, chapter:Chapter){
            try {
                val pages: MutableList<String> = ArrayList()
                var url = chapter.url
                var chapter_url = url?.subSequence(0,url.length-2).toString()
                val doc = Jsoup.connect(chapter.url).get().body().select("div[class=showchapterpagination]")
                var numberOfpgaes = doc.select("a").last()?.text()?.toInt()
                for (i in 1 until numberOfpgaes!!) {
                    var page = chapter_url+"$i"
                    val doc1 = Jsoup.connect(page).get().body().select("div[id=showchaptercontainer]>a>div").select("img[class=lazy]").attr("src");
                    Log.d("URL1",doc1)
                    pages.add(doc1)
                }
                data.postValue(pages)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        fun getMoreDetails(data: MutableLiveData<MangaAr>, manga:MangaAr){
            var author: String? = null
            var desc: String? = null
            var tags: ArrayList<String> = ArrayList()

            try {
                val doc = Jsoup.connect(manga.url).get().body()
                author = doc.select("div[class=manga-details-author]").select("h4>a").first()!!.text()
                var div : Elements = doc.select("div[class=manga-details-extended]")
                var h4: Elements  = div.select("h4")
                desc = h4[2].text()
                val tags1 = div.select("ul").select("li>a")
                for (tag in tags1) {
                        tags.add(tag.text())
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            manga.author = author
            manga.description = desc
            manga.tags = tags
            data.postValue(manga)
        }

    }
}