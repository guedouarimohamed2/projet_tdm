package com.a0.projet1.master.projet.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.a0.projet1.master.projet.Model.Annonce
import com.a0.projet1.master.projet.ViewImages

class SectionsPagerAdapter (fm: FragmentManager,annonce : Annonce) : FragmentPagerAdapter(fm)  {
    var a= annonce

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return ViewImages.newInstance(position + 1,a)
    }


    override fun getCount(): Int {
        // Show 3 total pages.
        return a.images!!.size
    }
}