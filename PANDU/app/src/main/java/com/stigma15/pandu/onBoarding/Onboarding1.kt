package com.stigma15.pandu.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.fragment_onboarding1.view.*

class Onboarding1 : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding1, container, false)

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.nextd.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return view
    }
}