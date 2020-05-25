package com.brandtwelch.pictureme.db.dao

import androidx.room.Query

interface PostDao {
    @Query("SELECT * FROM post WHERE original_poster_id=:id")
    fun getPostByProfileId(id: Int)
}