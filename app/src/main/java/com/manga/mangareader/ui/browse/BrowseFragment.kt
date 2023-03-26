package com.manga.mangareader.ui.browse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.manga.mangareader.R

class BrowseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_browse, container, false)
        var browsePagerAdapter : BrowsePagerAdapter = BrowsePagerAdapter(requireActivity().supportFragmentManager)
        browsePagerAdapter.setFragments()
        var viewPager:ViewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = browsePagerAdapter
        var tab : TabLayout = view.findViewById(R.id.tabs)
        tab.setupWithViewPager(viewPager)
        return  view
    }

    companion object {
        @JvmStatic
        fun newInstance() = BrowseFragment()
    }
}