package com.brandtwelch.pictureme.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class Post(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "original_poster_id") val originalPosterId: Int,
    @ColumnInfo(name = "timestamp") val timestamp: Timestamp,
    @ColumnInfo(name = "post_body") val postBody: String,
    @ColumnInfo(name = "number_comments") var numberComments: Int = 0,
    @ColumnInfo(name = "number_likes") var numberLikes: Int = 0
)