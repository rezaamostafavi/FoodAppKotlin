package com.mostafavi.home.food.util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

class Utils {
    companion object {
        fun getStatusBarHeight(context: Context): Int {
            var result = 0
            val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
            return result
        }

        fun getHeightScreen(context: Activity): Float {
            val displayMetrics = DisplayMetrics()
            context.windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.heightPixels.toFloat()
        }
    }
}