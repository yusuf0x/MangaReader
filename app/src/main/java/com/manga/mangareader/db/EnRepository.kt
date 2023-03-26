package com.manga.mangareader.db

import androidx.lifecycle.LiveData
import com.manga.mangareader.model.Manga
import com.manga.mangareader.model.MangaAr

class EnRepository(private  val dataDao: MangaEnDao) {
    val allItems:LiveData<List<Manga>> = dataDao.all()
     fun insert(item: Manga) {
        Thread {
            //Do your database´s operations here
            dataDao.add(item)
        }.start()
    }
     fun delete(item: Manga) {
         Thread {
             //Do your database´s operations here
             dataDao.delete(item)
         }.start()
//        dataDao.delete(item)
    }
}