package com.lvh.testandroid.view.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lvh.testandroid.api.ApiBuilder
import com.lvh.testandroid.base.BaseViewModel
import com.lvh.testandroid.model.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginViewModel : BaseViewModel() {
    private val userResponse = MutableLiveData<UserEntity>()

    fun showUserInfo(): MutableLiveData<UserEntity> {
        return userResponse
    }

//    fun login(email: String?, password: String?) {
//        disposables.add(ApiBuilder.getWebService().login(email, password)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe { showLoading(true) }
//                .doFinally { showLoading(false) }
//                .subscribe({
//                    userResponse.value = it.data
//                }, {
//                    showFailure(it)
//                }))
//    }

    override fun onCleared() {
        Log.d("UserViewModel", "onCleared")
        disposables.clear()
    }
}
