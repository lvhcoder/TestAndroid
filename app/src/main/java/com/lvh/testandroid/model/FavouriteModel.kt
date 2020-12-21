package com.lss.arigatou.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavouriteModel(
        @PrimaryKey
        @ColumnInfo(name = "user_id")
        val user_id: String,
        @ColumnInfo(name = "name")
        val name: String?,

        @ColumnInfo(name = "email")
        val email: String?,

        @ColumnInfo(name = "birthay")
        val birthay: String?,

        @ColumnInfo(name = "address")
        val address: String?,

        @ColumnInfo(name = "phone")
        val phone: String?,

        @ColumnInfo(name = "password")
        val password: String?
)
