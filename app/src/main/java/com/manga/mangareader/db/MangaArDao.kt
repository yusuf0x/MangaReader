package com.manga.mangareader.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.manga.mangareader.MTConstants
import com.manga.mangareader.model.MangaAr

@Dao
interface MangaArDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(manga:MangaAr)
    @Delete
    fun delete(manga: MangaAr)
    @Query("SELECT * FROM "+MTConstants.MANGA_AR_TABLE)
    fun all() : LiveData<List<MangaAr>>
}