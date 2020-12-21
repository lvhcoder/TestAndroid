package com.lvh.testandroid.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.lss.arigatou.model.FavouriteModel
import com.lvh.testandroid.model.UserEntity


@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favorite_table")
    fun getAll(): List<FavouriteModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favouriteModel: FavouriteModel?)

    @Query("DELETE FROM favorite_table ")
    fun deleteAll()

}