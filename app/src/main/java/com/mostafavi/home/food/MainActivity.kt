package com.mostafavi.home.food

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.Gson
import com.mostafavi.home.food.Data.Food
import com.mostafavi.home.food.Data.User
import com.mostafavi.home.food.adapter.FoodAdapter
import com.mostafavi.home.food.interfaces.ListItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var foods: ArrayList<Food>? = null

    private var adapter: FoodAdapter? = null
    private lateinit var mContext: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this;
        initToolbar()
        initFoods()
        initFoodsList()
    }


    private fun initFoodsList() {
        val columns: Int = resources.getInteger(R.integer.gridColumns)
        rvFoods.layoutManager = GridLayoutManager(mContext, columns)
        adapter = FoodAdapter(mContext, foods)
        rvFoods.adapter = adapter
        adapter!!.setItemClickListener(object : ListItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(mContext, FoodActivity::class.java)
                intent.putExtra("food", Gson().toJson(foods?.get(position)))
                val imgImage = view.findViewById<View>(R.id.imgImage)
                val tvDescription = view.findViewById<View>(R.id.tvDescription)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val imagePair = Pair(imgImage, imgImage.transitionName)
                    val descriptionPair = Pair(tvDescription, tvDescription.transitionName)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(mContext, imagePair/*, descriptionPair*/)
                    startActivity(intent, options.toBundle())
                } else
                    startActivity(intent)
            }
        })
    }

    private fun initFoods() {
        foods = ArrayList()
        foods!!.add(Food("", System.currentTimeMillis(), "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/best_and_worst_foods_for_your_liver_slideshow/493ss_thinkstock_rf_photo_of_berry_nut_oatmeal.jpg", "Oatmeal\n" + "Food with lots of fiber can help your liver work at its best. Want one that's a great way to start your day? Try oatmeal. Research shows it can help you shed some extra pounds and belly fat, which is a good way to keep away liver disease. ", 15, User("David", "http://www.messagescollection.com/wp-content/uploads/2015/04/cute-cat-profile-for-facebook.jpg")))
        foods!!.add(Food("", System.currentTimeMillis(), "https://www.prevention.com/sites/prevention.com/files/styles/listicle_main_custom_user_desktop_1x/public/766/healthy-eating-main.jpg?itok=7zvsamDP&timestamp=1516291165", "When the New Year rolls around, lots of people resolve to start eating healthier—whether it's cutting back carbs, eating more protein, or trying a specific diet. But taking on a food challenge for a whole year is, well, a challenge. While you might assume that eating healthy will instantly make you look and feel better, it can actually have some unexpected consequences on your system and your psyche, especially if you take on those changes too quickly.\n" +
                "\n" +
                "(Find out how to stop the craving cycle before it starts and burn fat around the clock with the naturally sweet, salty, and satisfying meals in Eat Clean, Lose Weight & Love Every Bite.)\n" +
                "\n" +
                "Of course, that's no reason NOT to make big improvements to how you eat. But by being aware of these often unexpected pitfalls from the start, you’ll be better mentally prepared to go the distance. We talked to Michelle Babb, RD, a Seattle-based specialist in functional nutrition and author of Anti-Inflammatory Eating for a Happy, Healthy Brain, to find out the unexpected pitfalls of a sudden diet change—and how to manage them so you can still crush your new healthy eating goals.", 120, User("Z haha", "https://cdn.dribbble.com/users/1289640/avatars/small/884328ffa07603532edc1a40bdef7c52.jpg?1498010366")))
        foods!!.add(Food("", System.currentTimeMillis(), "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/best_and_worst_foods_for_your_liver_slideshow/493s_thinkstock_rf_photo_of_pouring_cup_hot_green_tea.jpg", "Green Tea\n" +
                "It's brimming with a type of antioxidant called catechins. Research suggests it may protect against some forms of cancer, including liver. You'll get more catechins if you brew tea yourself and drink it hot. Iced tea and ready-to-drink green teas have much lower levels.", 17, User("Nancy", "http://wallpaper-gallery.net/images/profile-pictures/profile-pictures-2.jpg")))
        foods!!.add(Food("", System.currentTimeMillis(), "https://www.prevention.com/sites/prevention.com/files/styles/article_main_custom_user_desktop_1x/public/articles/2018/01/gettyimages-673116043.jpg?itok=SI-l78Ik&timestamp=1515090906", "When the National Heart, Lung, and Blood Institute tested the DASH (Dietary Approaches to Stop Hypertension) diet, the results were dramatic. Lower blood pressures showed up within two weeks. Here's an overview of the diet, which tied the Mediterranean Diet for \"Best Diets Overall\" in U.S. News & World Report's 2018 assessment of the year's healthiest eating plans.\n" +
                "\n" +
                "If you need to lose weight, choose the 1,600-calorie version outlined below. To further cut calories, the researchers who developed the diet suggest you also limit foods with added sugar, such as flavored yogurts, fruit drinks, and fruits canned in heavy syrup (rather than in their own juice.) And watch out for these 6 foods you're buying that are secret sugar bombs.", 1, User("Deepti Negi", "https://appamatix.com/wp-content/uploads/2016/05/cute-02-doge.jpg")))
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(R.string.app_name)
    }
}