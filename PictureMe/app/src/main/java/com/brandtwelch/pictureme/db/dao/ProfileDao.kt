package com.brandtwelch.pictureme.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.brandtwelch.pictureme.db.entities.Profile

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun getProfileByName(first: String, last: String): Profile
}