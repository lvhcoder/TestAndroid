package com.lvh.testandroid.view.splash

import android.os.Bundle
import android.os.Handler
import com.lvh.testandroid.R
import com.lvh.testandroid.base.BaseActivity
import com.lvh.testandroid.common.Const
import com.lvh.testandroid.utils.SharePreferenceUtils
import com.lvh.testandroid.view.carousel.CarouselActivity
import com.lvh.testandroid.view.login.LoginActivity

class SplashActivity : BaseActivity() {
    var handler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getRootLayoutId(): Int {
       return R.layout.activity_splash
    }

    override fun setupView(savedInstanceState: Bundle?) {
        handler =Handler()
        handler?.postDelayed({
            loadScreen()
        }, 1000)
    }

    private fun loadScreen() {
        var isLogin : Boolean = SharePreferenceUtils.getInstances().getBoolean(Const.KEY_REMEMBER)
        if (isLogin){
            goToActivity(CarouselActivity::class.java)
        }else{
            goToActivity(LoginActivity::class.java)
        }

        finish()
    }
}