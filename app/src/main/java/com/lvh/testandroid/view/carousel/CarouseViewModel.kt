package com.lvh.testandroid.view.carousel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lss.arigatou.model.FavouriteModel
import com.lvh.testandroid.MyApplication
import com.lvh.testandroid.MyApplication.Companion.context
import com.lvh.testandroid.api.ApiBuilder
import com.lvh.testandroid.base.BaseViewModel
import com.lvh.testandroid.base.Event
import com.lvh.testandroid.db.DatabaseHelper
import com.lvh.testandroid.db.FavouriteRepository
import com.lvh.testandroid.model.UserEntity
import com.lvh.testandroid.model.UserSave
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.security.AccessControlContext


class CarouseViewModel : BaseViewModel() {
    private val userEntity = MutableLiveData<UserEntity>()
    var favouriteRepository: FavouriteRepository? = null
    var dbHelper = DatabaseHelper(context)
    fun showUserInfo(): MutableLiveData<UserEntity> {
        return userEntity
    }

    fun getDataUser() {
        disposables.add(ApiBuilder.getWebService().getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showLoading(true) }
                .doFinally { showLoading(false) }
                .subscribe({
                    userEntity.value = it.results.get(0)
                }, {
                    showFailure(it)
                }))
    }
    fun insertFavourite( userSave: UserSave){
        dbHelper.insertData(userSave)
    }

   fun getAll() : List<UserSave> {
       return dbHelper.getAllUser()
   }

    override fun onCleared() {
        Log.d("UserViewModel", "onCleared")
        disposables.clear()
    }
}
