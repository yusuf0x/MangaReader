package com.manga.mangareader.db

import androidx.lifecycle.LiveData
import com.manga.mangareader.model.MangaAr

class ArRepository(private  val dataDao: MangaArDao) {
    val allItems:LiveData<List<MangaAr>> = dataDao.all()

//     fun allitems() :LiveData<List<MangaAr>>{
//        return dataDao.all()
//    }

    fun insert(item: MangaAr) {
        Thread {
        //Do your database´s operations here
            dataDao.add(item)
        }.start()

    }
    fun delete(item: MangaAr) {
        Thread {
            //Do your database´s operations here
            dataDao.delete(item)
        }.start()
    }
}