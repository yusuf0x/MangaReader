package com.manga.mangareader.db.coverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.manga.mangareader.model.Chapter
import com.manga.mangareader.model.Manga
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

//    @TypeConverter
//    fun listToJsonString(value: List<String>): String = Gson().toJson(value)

//    @TypeConverter
//    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toMutableList()

    @TypeConverter
    fun listChaptersToJsonString(value: List<Chapter>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToListChpaters(value: String) = Gson().fromJson(value, Array<Chapter>::class.java).toMutableList()

    @TypeConverter
    fun fromList(value : List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<ArrayList<String>>(value)
//    @TypeConverter
//    fun fromString(stringListString: String): List<String> {
//        return stringListString.split(",").map { it }
//    }
//
//    @TypeConverter
//    fun toString(stringList: List<String>): String {
//        return stringList.joinToString(separator = ",")
//    }
}