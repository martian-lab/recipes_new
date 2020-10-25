package com.martianlab.data.sources.db_new.adapters

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.sqldelight.ColumnAdapter
import java.util.*

object Converters {
    val listOfStringsAdapter = object : ColumnAdapter<List<String>, String> {
        override fun decode(value: String) : List<String> = Gson().fromJson(value, object : TypeToken<List<String>>(){}.type)
        override fun encode(list: List<String>) = Gson().toJson(list)
    }

    val dateAdapter = object  : ColumnAdapter<Date, Long>{
        override fun decode(unixtime: Long): Date = Date(unixtime)
        override fun encode(date: Date): Long = date.time
    }


}