package com.lvh.testandroid

import android.app.Application
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class MyApplication :Application() , LifecycleObserver {

    companion object {
        var context: Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        setupLifecycleListener()
    }

    private fun setupLifecycleListener() {
        ProcessLifecycleOwner.get().lifecycle
                .addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        Log.d("My_Lifecycle", "Returning to foreground…")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onMoveToBackground() {
        Log.d("My_Lifecycle", "Moving to background…")
    }

}