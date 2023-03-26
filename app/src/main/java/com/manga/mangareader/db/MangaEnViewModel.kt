package com.manga.mangareader.db

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.manga.mangareader.model.Manga
import com.manga.mangareader.model.MangaAr

class MangaEnViewModel(application: Application) : AndroidViewModel(application){
    private val repository: EnRepository
    val allItems: LiveData<List<Manga>>

    init {
        Log.d(TAG, "Inside ViewModel init")
        val dao = MangaDb.getDatabase(application).mangaEnDao()
        repository = EnRepository(dao)
        allItems = repository.allItems
    }

     fun insert(item: Manga) { //= viewModelScope.launch {
        repository.insert(item)
    }

//    suspend fun update(item: MangaAr){//} = viewModelScope.launch {
//        repository.update(item)
//    }

     fun delete(item: Manga) {//= viewModelScope.launch {
        repository.delete(item)
    }

//    fun get(id: Long) = repository.get(id)
}