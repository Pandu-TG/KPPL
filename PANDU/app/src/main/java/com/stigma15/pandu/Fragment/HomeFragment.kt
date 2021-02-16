package com.stigma15.pandu.Fragment

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.stigma15.pandu.Activity.LoginActivity
import com.stigma15.pandu.Activity.MainActivity
import com.stigma15.pandu.Activity.ProfileActivity
import com.stigma15.pandu.Adapter.CardViewPopularHomeAdapter
import com.stigma15.pandu.Adapter.SliderHomeAdapter
import com.stigma15.pandu.MyData.MyDataPopular
import com.stigma15.pandu.MyData.MyDataRecomendation
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : androidx.fragment.app.Fragment() {
    private val list = ArrayList<MyDataPopular>()
    private val listd = ArrayList<MyDataRecomendation>()
    private val sliderHomeAdapter = SliderHomeAdapter(listd)
    private lateinit var auth: FirebaseAuth
    lateinit var searchFragment: SearchFragment


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if(list.isEmpty()){
            list.addAll(getListMyDatas())
        }
        if(listd.isEmpty()){
            listd.addAll(getListMyDatasd())
        }
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_mydata.setHasFixedSize(true)
        auth = Firebase.auth
        showRecyclerCardView()
        showViewPager()
        setupIndicators()
        setCurrentIndicator(0)

        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)

        } else{
            updateUI(currentUser)
        }


        btn_profill.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
        }
        search_btn.setOnClickListener {
            (activity as MainActivity?)!!.searchPage()
        }

        rec_container.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
    }

    fun getListMyDatas(): ArrayList<MyDataPopular> {
        val dataName = resources.getStringArray(R.array.data_namee)
        val dataLocation = resources.getStringArray(R.array.data_locatione)
        val dataPhoto = resources.getStringArray(R.array.data_photoe)
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

    private fun updateUI(currentUser: FirebaseUser) {
        currentUser?.let {
            val username = currentUser.displayName
            val photoUrl = currentUser.photoUrl

            btn_profill.setImageURI(photoUrl)
            if (TextUtils.isEmpty(username)) {
                btn_profill.setImageURI(photoUrl)
            } else
                Glide.with(this)
                    .load(photoUrl)
                    .circleCrop()
                    .into(btn_profill)
        }
    }
    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        }*/

}