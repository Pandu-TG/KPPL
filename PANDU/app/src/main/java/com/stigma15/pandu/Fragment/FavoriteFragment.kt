package com.stigma15.pandu.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.stigma15.pandu.Adapter.CardViewFavoriteAdapter
import com.stigma15.pandu.MyData.MyDataFavorite
import com.stigma15.pandu.R
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_home.*

class FavoriteFragment : Fragment() {
    private val listt = ArrayList<MyDataFavorite>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_mydata_fav.setHasFixedSize(true)
        listt.addAll(getListMyData())
        showRecyclerCardView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }
    fun getListMyData(): ArrayList<MyDataFavorite> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataLocation = resources.getStringArray(R.array.data_location)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listMyData = ArrayList<MyDataFavorite>()
        for (position in dataName.indices) {
            val myDatad = MyDataFavorite(
                dataName[position],
                dataLocation[position],
                dataPhoto[position]
            )
            listMyData.add(myDatad)
        }
        return listMyData
    }
    private fun showRecyclerCardView() {
        rv_mydata_fav.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val cardViewFavoriteHome = CardViewFavoriteAdapter(listt)
        rv_mydata_fav.adapter = cardViewFavoriteHome
    }


}