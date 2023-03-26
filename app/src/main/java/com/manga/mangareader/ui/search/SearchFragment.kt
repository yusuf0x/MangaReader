package com.manga.mangareader.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.manga.mangareader.R

class SearchFragment : Fragment() ,SearchView.OnQueryTextListener{
    private lateinit var searchView: SearchView
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_search, container, false)
        searchView = view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);

        viewPager = view.findViewById(R.id.view_pager);
        tabs = view.findViewById(R.id.tabs);
        return  view
    }
    private fun update(query:String){
        var searchPagerAdapter  = SearchPagerAdapter(requireActivity().supportFragmentManager)
        searchPagerAdapter.setFragments(query)
        viewPager.adapter = searchPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }
    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        update(p0!!)
        searchView.clearFocus()
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }
}