package com.mostafavi.home.food

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.view.View
import com.google.gson.Gson
import com.mostafavi.home.food.Data.Food
import com.mostafavi.home.food.util.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_food.*
import java.text.SimpleDateFormat
import java.util.*
import com.mostafavi.home.food.R.id.fab
import com.mostafavi.home.food.R.id.tvDescription
import android.animation.ObjectAnimator
import android.view.MenuItem


class FoodActivity : AppCompatActivity() {
    private var food: Food? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        initBundle()
        initToolBar()
        initView()
        initTransitionSettings()
    }


    private fun initBundle() {
        if (intent.hasExtra("food")) {
            food = Gson().fromJson(intent.getStringExtra("food"), Food::class.java)
        }
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            changeStatusBarColor(ContextCompat.getColor(this, R.color.colorTransparentStatusBar))
            toolbar.setPadding(0, Utils.getStatusBarHeight(this), 0, 0)
        }
    }

    fun changeStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = color
        }
    }

    private fun initView() {
        Picasso.with(this).load(food?.image).into(imgImage)
        Picasso.with(this).load(food?.user?.profilePicture).into(imgProfile)
        tvDate.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date(food?.dateTime!!))
        tvLike.text = food?.like.toString()
        tvDescription.text = food?.description
        tvName.text = food?.user?.name
        fab.setOnClickListener {
            food?.liked = !food?.liked!!
            if (food?.liked!!)
                fab.setImageResource(R.drawable.ic_heart)
            else
                fab.setImageResource(R.drawable.ic_disable_heart)
        }
    }

    private fun initTransitionSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val fade = Fade()
            fade.excludeTarget(android.R.id.statusBarBackground, true)
            fade.excludeTarget(android.R.id.navigationBarBackground, true)
            window.enterTransition = fade
            window.exitTransition = fade
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        ObjectAnimator.ofFloat(tvDescription, "translationY", 0f, (Utils.getHeightScreen (this))).setDuration(500).start()
        fab.visibility = View.GONE
        super.onBackPressed()
    }
}