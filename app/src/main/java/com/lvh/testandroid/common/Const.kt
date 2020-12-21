package com.lvh.testandroid.common

import com.lvh.testandroid.BuildConfig

class Const {
    companion object {
        val KEY_EXTRA_DATA: String = "data"
        val KEY_USER: String = "KEY_USER"
        var SERVER_ADDRESS = BuildConfig.API_URL
        var KEY_REMEMBER = "KEY_REMEMBER"
    }
}