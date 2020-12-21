package com.lvh.testandroid.view.carousel

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.lss.arigatou.model.FavouriteModel
import com.lvh.testandroid.R
import com.lvh.testandroid.adapter.MyPagerAdapter
import com.lvh.testandroid.base.BaseActivity
import com.lvh.testandroid.common.Const
import com.lvh.testandroid.common.OnSwipeTouchListener
import com.lvh.testandroid.db.FavouriteDatabase
import com.lvh.testandroid.model.UserEntity
import com.lvh.testandroid.model.UserSave
import com.lvh.testandroid.utils.SharePreferenceUtils
import com.lvh.testandroid.view.login.LoginActivity
import kotlinx.android.synthetic.main.main_carousel_activity.*


class CarouselActivity : BaseActivity() {
    private lateinit var viewModel: CarouseViewModel
    private var pagerAdapter: MyPagerAdapter? = null
    var fragmentList: ArrayList<Fragment> = ArrayList()
    private var lastPosition = 0;
    private var userEntity: UserEntity? = null
    private var database = FavouriteDatabase
    var favouriteModel : FavouriteModel ?=null

    var listFavourite: List<FavouriteModel>? = ArrayList();
    override fun getRootLayoutId(): Int {
        return R.layout.main_carousel_activity
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(CarouseViewModel::class.java)
        setObserveLive(viewModel)

        pagerAdapter = MyPagerAdapter(supportFragmentManager)
        pagerAdapter?.addFragments(ArrayList())

        loadData()
        imvName.setOnClickListener {
            setContent(1)
        }
        imvEmail.setOnClickListener {
            setContent(2)
        }
        imvAddress.setOnClickListener {
            setContent(3)
        }
        imvPhone.setOnClickListener {
            setContent(4)
        }
        imvPassword.setOnClickListener {
            setContent(5)
        }
        btnSignOut.setOnClickListener {
            SharePreferenceUtils.getInstances().saveBoolean(Const.KEY_REMEMBER,false)
            goToActivity(LoginActivity::class.java)
            finish()
        }
        btnListFavorite.setOnClickListener {
            var list = viewModel.getAll()
            Toast.makeText(this,"Total number of saved users " +list.size ,Toast.LENGTH_LONG).show()
        }
        nextView.setOnTouchListener(object : OnSwipeTouchListener(this) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                loadData()
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                saveUserFavourite()
            }
        })
    }

    private fun saveUserFavourite() {
        var userSave = UserSave(0,
                userEntity?.name?.last + userEntity?.name?.first,
                userEntity?.email,
                userEntity?.phone,
                userEntity?.location?.street?.number.toString() + userEntity?.location?.street?.name)
        viewModel?.insertFavourite(userSave)

    }

    private fun setContent(type: Int) {
        when (type) {
            1 -> {
                tvTitle.text = getString(R.string.name)
                tvContent.text = userEntity?.name?.last + userEntity?.name?.first

            }
            2 -> {
                tvTitle.text = getString(R.string.email)
                tvContent.text = userEntity?.email

            }

            3 -> {
                tvTitle.text = getString(R.string.address)
                tvContent.text = userEntity?.location?.street?.number.toString() + userEntity?.location?.street?.name
            }
            4 -> {
                tvTitle.text = getString(R.string.phone)
                tvContent.text = userEntity?.phone
            }
            5 -> {
                tvTitle.text = getString(R.string.password)
                tvContent.text = userEntity?.login?.password
            }
        }
    }

    private fun loadData() {
        viewModel.getDataUser()
        viewModel.showUserInfo().observe(this, Observer {
            setUpUser(it)
        })
    }

    private fun setUpUser(user: UserEntity) {
        userEntity = user
        tvTitle.text = getString(R.string.name)
        tvContent.text = user.name?.last + user.name?.first
        Glide.with(this).load(user.picture?.large).into(imvAvatar)
    }



}
