package com.lvh.testandroid.db

import android.content.Context
import androidx.annotation.WorkerThread
import com.lss.arigatou.model.FavouriteModel
import org.intellij.lang.annotations.Flow

class FavouriteRepository {
    var favouriteDao: FavouriteDao? = null

    constructor(context: Context) {
        val db = FavouriteDatabase.getInstance(context)
        favouriteDao = db?.favouriteDao()
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insert(favouriteModel: FavouriteModel?){
        favouriteDao?.insert(favouriteModel)
    }
    val allUser :List<FavouriteModel>? = favouriteDao?.getAll()
}