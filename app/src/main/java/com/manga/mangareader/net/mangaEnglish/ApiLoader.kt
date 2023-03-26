package com.manga.mangareader.net.mangaEnglish


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.Manga
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException


class ApiLoader {
    companion object{
        fun browse(mutableManga: MutableLiveData<List<Manga>>, page:Int, genre:String?){
            try {
                val mangaList: MutableList<Manga> = ArrayList()
                val pageUrl: String = if (genre == null) ApiBuilder.buildBrowse(page).toString() else ApiBuilder.buildCategoryBrowse(page, genre).toString()
                val doc: Element = Jsoup.connect(pageUrl).userAgent(Constant.USER_AGENT).get().body()
                for (manga in doc.select("li[class=mb-lg]")) {
                    val e: Elements = manga.select("div[class=subject-title]").select("a")
                    val title: String = e.attr("title")
                    val url: String = e.attr("href")
                    val summary: String = manga.select("p[class=desktop-only excerpt]").text()
                    val data: List<String> = manga.select("div[class=color-imdb]").text().split(" ")
                    var rating: String? = null
                    if (data.size >= 2) rating = data[1]
                    var art: String = if (genre == null) manga.select("div[class=poster-with-subject]")
                            .select("img")
                            .attr("src") else manga.select("div[class=poster-with-subject]")
                            .select("img").attr("data-src")
                    val tags: ArrayList<String> = ArrayList()
                    val tagList: Elements = manga.select("span[class=genres]").select("a")
                    for (tag in tagList) {
                        tags.add(tag.attr("title"))
                    }

                    val m = Manga(title, url, summary, rating, art, tags)
                    mangaList.add(m)
                }
                mutableManga.postValue(mangaList)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun getChapters(chapters: MutableLiveData<List<Chapter>>, manga:Manga){
            try {
                var chapterList: MutableList<Chapter> = ArrayList()
                val doc = Jsoup.connect(ApiBuilder.buildCombo(manga.url)).get().body().select("section[class=episodes-box]").select("table[class=ui basic unstackable table]")
                for (chp in doc) {
                    var title = chp.select("a").text()
                    var url = chp.select("a").attr("href")
                    var pub = chp.select("td[class=episode-date]").text()
                    chapterList.add(Chapter(title, url, pub, null))
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
                val doc = Jsoup.connect(ApiBuilder.buildCombo(chapter.url)).get().body().select("img[class=img-responsive scroll-down]")
                for (pg in doc) {
                    var page = pg.attr("src")
                    pages.add(page)
                }
                data.postValue(pages)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        fun getMoreDetails(data: MutableLiveData<Manga>, manga:Manga){
            var author: String? = null
            var status: String? = null

            try {
                val doc = Jsoup.connect(ApiBuilder.buildCombo(manga.url)).get().body()
                author = doc.select("div[class=sub-title pt-sm]").text()
                status = doc.select("span[class=series-status aqua]").text()
                if (manga.summary.equals("")) manga.summary =
                    doc.select("div[class=series-summary-wrapper]").select("p").select("span")
                        .text()
                if (manga.rating.equals("0")) manga.rating =
                    doc.select("div[class=color-imdb]").text()
                if (manga.tags == null) {
                    manga.tags = ArrayList()
                    for (gen in doc.select("div[class=series-summary-wrapper]").select("div[class=ui list]").select("a"))
                        manga.tags!!.add(gen.attr("title"))
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            manga.author = author
            manga.status = status
            data.postValue(manga)
        }
        fun search(mutableManga: MutableLiveData<List<Manga>>, query: String) {
            try {
                val mangaList: MutableList<Manga> = ArrayList()
                val data: HashMap<String, String> = HashMap()
                data["dataType"] = "json"
                data["phrase"] = query
                val headers: HashMap<String, String> = HashMap()
                headers["X-Requested-With"] = "XMLHttpRequest"
                headers["Cache-Control"] = "public max-age=604800"
                val doc = Jsoup.connect("https://www.readm.org/service/search")
                    .headers(headers)
                    .data(data)
                    .ignoreContentType(true)
                    .post().select("body").text()
                Log.d("QUERY",query)
                Log.d("JSON",doc.toString())
                val json = JSONObject(doc)
                val array = json.getJSONArray("manga")
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    var title: String = ""
                    var url: String? = null
                    var art: String? = null
                    if (obj.has("title")) title = obj.getString("title")
                    if (obj.has("url")) url = obj.getString("url")
                    if (obj.has("image")) art = obj.getString("image")
                    val m = Manga(title, url, null, "0", art, null)
                    mangaList.add(m)
                }
                mutableManga.postValue(mangaList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }
}