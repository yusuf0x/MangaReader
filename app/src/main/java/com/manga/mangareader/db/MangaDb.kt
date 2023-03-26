package com.manga.mangareader.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manga.mangareader.MTConstants
import com.manga.mangareader.db.coverters.Converters
import com.manga.mangareader.model.Manga
import com.manga.mangareader.model.MangaAr

@TypeConverters(Converters::class)
@Database(entities = [MangaAr::class,Manga::class], version = MTConstants.DATABASE_VERSION, exportSchema = false)
abstract class MangaDb:RoomDatabase() {
    abstract fun mangaEnDao(): MangaEnDao
    abstract fun mangaArDao(): MangaArDao
    companion object {
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getDatabase(context: Context): MangaDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance as MangaDb
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,MangaDb::class.java,
                    MTConstants.MANGA_DB).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}