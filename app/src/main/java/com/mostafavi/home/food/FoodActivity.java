package com.mostafavi.home.food;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mostafavi.home.food.Data.Food;
import com.mostafavi.home.food.util.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FoodActivity extends AppCompatActivity {

    private Food food;
    private ImageView imgImage, imgProfile;
    private TextView tvDate, tvLike, tvUserName, tvDescription;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        initBundle();
        init();
        initToolbar();
        if (food == null)
            return;
        initView();
        initTransitionSettings();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        ObjectAnimator.ofFloat(tvDescription, "translationY", 0, Utils.getHeightScreen(this)).setDuration(500).start();
        fab.setVisibility(View.GONE);
        super.onBackPressed();
    }

    private void initTransitionSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);
        }
    }

    private void initView() {
        Picasso.with(this).load(food.getImage()).into(imgImage);
        Picasso.with(this).load(food.getUser().getProfilePicture()).into(imgProfile);
        tvDate.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK).format(new Date(food.getDateTime())));
        tvLike.setText(String.valueOf(food.getLike()));
        tvDescription.setText(food.getDescription());
        tvUserName.setText(food.getUser().getName());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food.setLiked(!food.isLiked());
                if (food.isLiked()) {
                    fab.setImageResource(R.drawable.ic_heart);
                } else
                    fab.setImageResource(R.drawable.ic_disable_heart);

            }
        });
        ObjectAnimator.ofFloat(tvDescription, "translationY", Utils.getHeightScreen(this), 0).setDuration(500).start();
    }

    private void initBundle() {
        if (getIntent() != null) {
            if (getIntent().hasExtra("food")) {
                food = new Gson().fromJson(getIntent().getStringExtra("food"), Food.class);
            }
        }
    }

    private void init() {
        imgImage = (ImageView) findViewById(R.id.imgImage);
        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvLike = (TextView) findViewById(R.id.tvLike);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvUserName = (TextView) findViewById(R.id.tvName);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    protected void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            changeStatusBarColor(ContextCompat.getColor(this, R.color.colorTransparentStatusBar));
            toolbar.setPadding(0, Utils.getStatusBarHeight(this), 0, 0);
        }
    }

    private void changeStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(color);
        }
    }


}
