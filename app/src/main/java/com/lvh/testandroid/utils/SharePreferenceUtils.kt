package com.lvh.testandroid.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.lvh.testandroid.MyApplication
import com.lvh.testandroid.common.Const
import com.lvh.testandroid.model.UserEntity


class SharePreferenceUtils {
    val name = "BASEKOTLIN"

    private constructor() {
        mPrefs = MyApplication.context?.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    companion object {
        private var mPrefs: SharedPreferences? = null
        private var instance: SharePreferenceUtils = SharePreferenceUtils()
        fun getInstances(): SharePreferenceUtils {
            if (instance == null) {
                instance = SharePreferenceUtils()
            }
            return instance
        }
    }

    fun saveString(key: String, content: String?) {
        mPrefs?.edit()?.putString(key, content)?.apply()
    }

    fun removeString(key: String) {
        mPrefs?.edit()?.remove(key)?.apply()
    }

    fun getString(key: String): String? {
        return mPrefs?.getString(key, "")
    }

    fun saveInt(key: String, content: Int?) {
        content?.let {
            mPrefs?.edit()?.putInt(key, content)?.apply()
        }
    }

    fun getInt(key: String): Int {
        return mPrefs?.getInt(key, -1) ?: -1
    }
    fun saveBoolean(key: String, content: Boolean?) {
        if (content != null) {
            mPrefs?.edit()?.putBoolean(key, content)?.apply()
        }
    }
    fun getBoolean(key: String): Boolean {
        return mPrefs?.getBoolean(key, false) ?: false
    }
    fun saveUserInfor(userInfor: UserEntity?) {
        val gson = Gson()
        val json = gson.toJson(userInfor)
        mPrefs?.edit()?.putString(Const.KEY_USER, json)?.apply()
    }
    fun getUserInfor(): UserEntity? {
        try {
            val json = mPrefs?.getString(Const.KEY_USER, null) ?: return null
            return Gson().fromJson(json, UserEntity::class.java)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }
}