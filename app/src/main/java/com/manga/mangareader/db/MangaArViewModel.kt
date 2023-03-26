package com.manga.mangareader.db

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.manga.mangareader.model.MangaAr

class MangaArViewModel(application: Application) : AndroidViewModel(application){
    private val repository: ArRepository
    val allItems: LiveData<List<MangaAr>>

    init {
        Log.d(TAG, "Inside ViewModel init")
        val dao = MangaDb.getDatabase(application).mangaArDao()
        repository = ArRepository(dao)
        allItems = repository.allItems
    }

    fun insert(item: MangaAr) { //= viewModelScope.launch {
        repository.insert(item)
    }

//    suspend fun update(item: MangaAr){//} = viewModelScope.launch {
//        repository.update(item)
//    }

    fun delete(item: MangaAr) {//= viewModelScope.launch {
        repository.delete(item)
    }

//    fun get(id: Long) = repository.get(id)
}