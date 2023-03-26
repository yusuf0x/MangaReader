package com.manga.mangareader

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.TypedValue
import androidx.core.content.ContextCompat


class ThemeHelper {
    companion object {
        fun getThemeMap(): HashMap<Int, Int>? {
            val themes: HashMap<Int, Int> = HashMap()
            themes[R.color.red] = R.style.BaseTheme_Red
            themes[R.color.pink] = R.style.BaseTheme_Pink
            themes[R.color.purple] = R.style.BaseTheme_Purple
            themes[R.color.deep_purple] = R.style.BaseTheme_DeepPurple
            themes[R.color.indigo] = R.style.BaseTheme_Indigo
            themes[R.color.blue] = R.style.BaseTheme_Blue
            themes[R.color.light_blue] = R.style.BaseTheme_LightBlue
            themes[R.color.cyan] = R.style.BaseTheme_Cyan
            themes[R.color.teal] = R.style.BaseTheme_Teal
            themes[R.color.green] = R.style.BaseTheme_Green
            themes[R.color.light_green] = R.style.BaseTheme_LightGreen
            themes[R.color.lime] = R.style.BaseTheme_Lime
            themes[R.color.yellow] = R.style.BaseTheme_Yellow
            themes[R.color.amber] = R.style.BaseTheme_Amber
            themes[R.color.orange] = R.style.BaseTheme_Orange
            themes[R.color.deep_orange] = R.style.BaseTheme_DeepOrange
            themes[R.color.brown] = R.style.BaseTheme_Brown
            themes[R.color.grey] = R.style.BaseTheme_Grey
            themes[R.color.blue_grey] = R.style.BaseTheme_BlueGrey
            themes[R.color.red_300] = R.style.BaseTheme_Red300
            themes[R.color.pink_300] = R.style.BaseTheme_Pink300
            themes[R.color.purple_300] = R.style.BaseTheme_Purple300
            themes[R.color.deep_purple_300] = R.style.BaseTheme_DeepPurple300
            themes[R.color.indigo_300] = R.style.BaseTheme_Indigo300
            themes[R.color.blue_300] = R.style.BaseTheme_Blue300
            themes[R.color.light_blue_300] = R.style.BaseTheme_LightBlue300
            themes[R.color.cyan_300] = R.style.BaseTheme_Cyan300
            themes[R.color.teal_300] = R.style.BaseTheme_Teal300
            themes[R.color.green_300] = R.style.BaseTheme_Green300
            themes[R.color.light_green_300] = R.style.BaseTheme_LightGreen300
            themes[R.color.lime_300] = R.style.BaseTheme_Lime300
            themes[R.color.yellow_300] = R.style.BaseTheme_Yellow300
            themes[R.color.amber_300] = R.style.BaseTheme_Amber300
            themes[R.color.orange_300] = R.style.BaseTheme_Orange300
            themes[R.color.deep_orange_300] = R.style.BaseTheme_DeepOrange300
            themes[R.color.brown_300] = R.style.BaseTheme_Brown300
            themes[R.color.grey_300] = R.style.BaseTheme_Grey300
            themes[R.color.blue_grey_300] = R.style.BaseTheme_BlueGrey300
            return themes
        }
        fun getTheme(accentColor: Int): Int? {
            return getThemeMap()!![accentColor!!]
        }

        fun applySettings(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            activity.finishAfterTransition()
            activity.startActivity(intent)
            activity.overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
        }

        fun resolveColorAttr(context: Context, attr: Int): Int {
            val resolveTheme = resolveThemeAttr(context, attr)
            val color =
                if (resolveTheme.resourceId != 0) resolveTheme.resourceId else resolveTheme.data
            return ContextCompat.getColor(context, color)
        }

        private fun resolveThemeAttr(context: Context, attr: Int): TypedValue {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(attr, typedValue, true)
            return typedValue
        }

    }
}