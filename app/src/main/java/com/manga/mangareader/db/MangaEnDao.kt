package com.manga.mangareader.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.manga.mangareader.MTConstants
import com.manga.mangareader.model.Manga
import com.manga.mangareader.model.MangaAr

@Dao
interface MangaEnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(manga: Manga)
    @Delete
    fun delete(manga: Manga)
    @Query("SELECT * FROM "+ MTConstants.MANGA_EN_TABLE)
    fun all() : LiveData<List<Manga>>
}