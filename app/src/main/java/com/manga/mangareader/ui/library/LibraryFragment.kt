package com.manga.mangareader.ui.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.manga.mangareader.R
import com.manga.mangareader.ui.browse.BrowsePagerAdapter


class  LibraryFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_library, container, false)
        var libraryPagerAdapter  = LibraryPagerAdapter(requireActivity().supportFragmentManager)
        libraryPagerAdapter.setFragments()
        var viewPager: ViewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = libraryPagerAdapter
        var tab : TabLayout = view.findViewById(R.id.tabs)
        tab.setupWithViewPager(viewPager)
        return view;
    }

    companion object {

        @JvmStatic
        fun newInstance() = LibraryFragment()
    }
}