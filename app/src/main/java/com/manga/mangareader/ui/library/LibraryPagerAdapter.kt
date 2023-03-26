package com.manga.mangareader.ui.library

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.manga.mangareader.ui.library.arabic.MangaArabicLibraryFragment
import com.manga.mangareader.ui.library.english.MangaEnglishLibraryFragment

class LibraryPagerAdapter(private val fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    private val fragmentList = mutableListOf<Fragment>()
    private val fragmentTitleList= mutableListOf<String> ("English","العربية")

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
    fun setFragments() {
        fragmentList.add(MangaEnglishLibraryFragment.newInstance())
        fragmentList.add(MangaArabicLibraryFragment.newInstance())
    }
}