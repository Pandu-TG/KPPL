package com.stigma15.pandu.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stigma15.pandu.Activity.ProfileActivity
import com.stigma15.pandu.Adapter.CardViewPopularHomeAdapter
import com.stigma15.pandu.Adapter.SliderHomeAdapter
import com.stigma15.pandu.MyData.MyDataPopular
import com.stigma15.pandu.MyData.MyDataRecomendation
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private val list = ArrayList<MyDataPopular>()
    private val listd = ArrayList<MyDataRecomendation>()
    private val sliderHomeAdapter = SliderHomeAdapter(listd)
    lateinit var searchFragment: SearchFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_mydata.setHasFixedSize(true)
        list.addAll(getListMyDatas())
        listd.addAll(getListMyDatasd())
        showRecyclerCardView()
        showViewPager()
        setupIndicators()
        setCurrentIndicator(0)

        search_btn.setOnClickListener {

            searchFragment = SearchFragment()
            parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, searchFragment)
                    .setCustomAnimations(android.R.anim.slide_in_left, 0, 0, android.R.anim.slide_out_right)
                    .commit() }


        rec_container.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

    }

    fun getListMyDatas(): ArrayList<MyDataPopular> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataLocation = resources.getStringArray(R.array.data_location)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listMyData = ArrayList<MyDataPopular>()
        for (position in dataName.indices) {
            val myDatad = MyDataPopular(
                dataName[position],
                dataLocation[position],
                dataPhoto[position]
            )
            listMyData.add(myDatad)
        }
        return listMyData
    }
    fun getListMyDatasd(): ArrayList<MyDataRecomendation> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataLocation = resources.getStringArray(R.array.data_location)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listMyDatad = ArrayList<MyDataRecomendation>()
        for (position in dataName.indices) {
            val myData = MyDataRecomendation(
                dataName[position],
                dataLocation[position],
                dataPhoto[position]
            )
            listMyDatad.add(myData)
        }
        return listMyDatad
    }
    private fun showViewPager(){
        rec_container.adapter = sliderHomeAdapter
    }

    private fun showRecyclerCardView() {
        rv_mydata.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val cardViewPopularHome = CardViewPopularHomeAdapter(list)
        rv_mydata.adapter = cardViewPopularHome
    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(sliderHomeAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(context)
            indicators[i].apply {
                this?.setImageDrawable(
                        ContextCompat.getDrawable(
                                context,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index){
                imageView.setImageDrawable(
                        context?.let {
                            ContextCompat.getDrawable(
                                    it,
                                    R.drawable.indicator_active
                            )
                        }
                )
            }else
            {
                imageView.setImageDrawable(
                        context?.let {
                            ContextCompat.getDrawable(
                                    it,
                                    R.drawable.indicator_inactive
                            )
                        }
                )

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_profil.setOnClickListener {
            val intent = Intent (activity, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}