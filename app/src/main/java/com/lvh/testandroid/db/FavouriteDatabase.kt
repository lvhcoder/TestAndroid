package com.lvh.testandroid.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lss.arigatou.model.FavouriteModel

@Database(entities = [(FavouriteModel::class)], version = 1, exportSchema = false)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao

    companion object {
        private var INSTANCE: FavouriteDatabase? = null

        fun getInstance(context: Context): FavouriteDatabase? {
            if (INSTANCE == null) {
                synchronized(FavouriteDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                                    context.applicationContext,
                                    FavouriteDatabase::class.java, "favourite_user"
                            )
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}