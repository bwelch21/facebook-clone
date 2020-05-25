package com.brandtwelch.pictureme.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brandtwelch.pictureme.db.dao.ProfileDao
import com.brandtwelch.pictureme.db.entities.Profile

@Database(entities = arrayOf(Profile::class), version = 1, exportSchema = false)
abstract class KnowMeDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}