package com.lvh.testandroid.view.carousel

import android.view.View
import com.lvh.testandroid.R
import com.lvh.testandroid.base.BaseFragment
import com.lvh.testandroid.model.UserEntity
import kotlinx.android.synthetic.main.main_carousel_activity.*


class CarouseItemFragment : BaseFragment() {
    private var item: UserEntity? = null

    companion object {
        fun newInstance(): CarouseItemFragment {
            return CarouseItemFragment()
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.fragment_carouse_item
    }

    override fun setupViewModel() {
    }

    fun loadData(userEntity: UserEntity?) {
        item = userEntity

    }

    override fun setupUI(view: View) {

    }



}
