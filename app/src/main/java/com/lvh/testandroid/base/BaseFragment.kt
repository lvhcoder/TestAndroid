package com.lvh.testandroid.base

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lvh.testandroid.common.DialogUtils
import kotlinx.android.synthetic.main.view_header.*

abstract class BaseFragment : Fragment() {
    protected val TAG = BaseFragment::class.java.simpleName
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getRootLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupUI(view)
    }

    abstract fun getRootLayoutId(): Int

    abstract fun setupViewModel()

    abstract fun setupUI(view: View)

    private lateinit var mProgressDialog: Dialog

    private fun showLoadingDialog() {
        if (isVisible) {
            DialogUtils.showLoadingDialog(activity)
        }
    }

    fun setTitleToolbar(title: String? = "") {
        ln_left.visibility = View.INVISIBLE
        tv_title.text = title
    }

    private fun hideLoadingDialog() {
        DialogUtils.dismiss()
    }

    fun setObserveLive(viewModel: BaseViewModel) {
        viewModel.eventLoading.observe(this, Observer {
            if (it != null) {
                if (it.getContentIfNotHandled() != null) {
                    if (it.peekContent()) {
                        showLoadingDialog()
                    } else {
                        hideLoadingDialog()
                    }
                }
            }
        })
        viewModel.eventFailure.observe(this, Observer {
            if (it != null) {
                if (it.getContentIfNotHandled() != null) {
                    showFailure(it.peekContent())
                }
            }
        })
    }

    private fun showFailure(throwable: Throwable) {
        if (throwable.message != null) {
            Log.i(TAG, "showQuestFailure: " + throwable.message)
        }
    }

    fun goToActivity(c: Class<*>, bundle: Bundle? = null) {
        (activity as? BaseActivity)?.goToActivity(c, bundle)
    }
}