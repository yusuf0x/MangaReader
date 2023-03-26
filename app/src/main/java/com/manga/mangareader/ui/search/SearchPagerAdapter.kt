package com.manga.mangareader.ui.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.manga.mangareader.ui.browse.arabic.MangaArabicBrowseFragment
import com.manga.mangareader.ui.search.arabic.MangaArSearchFragment
import com.manga.mangareader.ui.search.english.MangaEnSearchFragment

class SearchPagerAdapter(private val fm: FragmentManager): FragmentStatePagerAdapter(fm)  {
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
    fun setFragments(query:String) {
        fragmentList.add(MangaEnSearchFragment.newInstance(query))
        fragmentList.add(MangaArSearchFragment.newInstance(query))
    }
}