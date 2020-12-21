package com.lvh.testandroid.common

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import com.lvh.testandroid.R
import kotlinx.android.synthetic.main.custom_progress_loading.view.*


object DialogUtils {
    var dialog: AlertDialog? = null

    fun showLoadingDialog(context: Context?) {
        var dialogBuilder = AlertDialog.Builder(context, R.style.CustomProgress)
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.custom_progress_loading, null)
        val gradientDrawable = GradientDrawable()
        dialogView?.progress_bar?.progressDrawable = gradientDrawable
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        dialog = dialogBuilder.create()
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
    }

}