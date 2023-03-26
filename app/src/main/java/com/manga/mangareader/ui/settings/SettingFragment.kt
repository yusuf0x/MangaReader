package com.manga.mangareader.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manga.mangareader.MTPreferences.getThemeMode
import com.manga.mangareader.MTPreferences.storeThemeMode
import com.manga.mangareader.R


class SettingFragment : Fragment() ,View.OnClickListener{
    private lateinit var accentView: RecyclerView
    private lateinit var chipLayout: LinearLayout
    private lateinit var currentThemeMode: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_setting, container, false)
        accentView = view.findViewById(R.id.accent_view);
        chipLayout = view.findViewById(R.id.chip_layout);
        currentThemeMode = view.findViewById(R.id.current_theme_mode);
        val accentOption = view.findViewById<LinearLayout>(R.id.accent_option)
        val themeModeOption = view.findViewById<LinearLayout>(R.id.theme_mode_option)
//        val githubOption = view.findViewById<LinearLayout>(R.id.github_option)
        setCurrentThemeMode();
        accentView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        accentView.adapter = AccentAdapter(requireActivity())

        accentOption.setOnClickListener(this)
        themeModeOption.setOnClickListener(this)
//        githubOption.setOnClickListener(this)

        view.findViewById<View>(R.id.night_chip).setOnClickListener(this)
        view.findViewById<View>(R.id.light_chip).setOnClickListener(this)
        view.findViewById<View>(R.id.auto_chip).setOnClickListener(this)

        return view
    }
    private fun setCurrentThemeMode() {
        val mode = getThemeMode(requireActivity().applicationContext)
        if (mode == AppCompatDelegate.MODE_NIGHT_NO) currentThemeMode.setImageResource(R.drawable.ic_theme_mode_light) else if (mode == AppCompatDelegate.MODE_NIGHT_YES) currentThemeMode.setImageResource(
            R.drawable.ic_theme_mode_night
        ) else currentThemeMode.setImageResource(R.drawable.ic_theme_mode_auto)
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = SettingFragment()
    }

    override fun onClick(p0: View?) {
        val id: Int = p0!!.getId()

        if (id == R.id.accent_option) {
            val visibility = if (accentView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            accentView.visibility = visibility
//        } else if (id == R.id.github_option) {
//            startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse(MTConstants.GITHUB_REPO_URL)
//                )
//            )
        } else if (id == R.id.theme_mode_option) {
            val mode = if (chipLayout.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            chipLayout.visibility = mode
        } else if (id == R.id.night_chip) selectTheme(AppCompatDelegate.MODE_NIGHT_YES)
        else if (id == R.id.light_chip) selectTheme(AppCompatDelegate.MODE_NIGHT_NO)
        else if (id == R.id.auto_chip) selectTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    private fun selectTheme(theme: Int) {
        AppCompatDelegate.setDefaultNightMode(theme)
        storeThemeMode(requireActivity().applicationContext, theme)
    }
}