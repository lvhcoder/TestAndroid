package com.lvh.testandroid.view.login

import android.os.Bundle
import android.text.TextUtils
import com.lvh.testandroid.R
import com.lvh.testandroid.base.BaseActivity
import com.lvh.testandroid.common.Const
import com.lvh.testandroid.common.extensions.isEmailValid
import com.lvh.testandroid.common.extensions.isValidPassword
import com.lvh.testandroid.utils.SharePreferenceUtils
import com.lvh.testandroid.view.carousel.CarouselActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    private lateinit var viewModel: LoginViewModel
    private var email: String = ""
    private var password: String = ""

    override fun getRootLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun setupView(savedInstanceState: Bundle?) {
        btnLogin.setOnClickListener {
            checkDataLogin();
        }

    }

    fun checkDataLogin() {
        email = edtEmail.text.toString().trim()
        password = edtPassword.text.toString().trim()
        if (TextUtils.isEmpty(email)) {
            edtEmail.error = getString(R.string.email_empty)
            return
        }
        if (!email.isEmailValid()) {
            edtEmail.error=getString(R.string.email_invalid)
            return
        }
        if (TextUtils.isEmpty(password)) {
            edtPassword.error=getString(R.string.password_empty)
            return
        }
        if (!password.isValidPassword()) {
            edtPassword.error=getString(R.string.password_more_than)
            return
        }
      setUpLogin()
    }

    private fun setUpLogin() {
        goToActivity(CarouselActivity::class.java)
        if (cbRemember.isChecked){
            SharePreferenceUtils.getInstances().saveBoolean(Const.KEY_REMEMBER,true)
        }else{
            SharePreferenceUtils.getInstances().saveBoolean(Const.KEY_REMEMBER,false)
        }
        finish()
    }

    override fun onBackPressed() {
        finish()
    }
}
