package com.stigma15.pandu.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.stigma15.pandu.MyData.MyDataFavorite
import com.stigma15.pandu.MyData.MyDataPopular
import com.stigma15.pandu.R

class CardViewFavoriteAdapter(private val listMyData: ArrayList<MyDataFavorite>) : RecyclerView.Adapter<CardViewFavoriteAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return CardViewViewHolder(view)
    }
    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val myData = listMyData[position]
        Glide.with(holder.itemView.context)
                .load(myData.img)
                .apply(RequestOptions().override(350, 550))
                .into(holder.imgPhoto)
        holder.tvName.text = myData.nama
        holder.tvDetail.text = myData.locationd
        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context, "Kamu memilih " +
                listMyData[holder.adapterPosition].nama, Toast.LENGTH_SHORT).show() }
    }
    override fun getItemCount(): Int {
        return listMyData.size
    }
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.fav_img)
        var tvName: TextView = itemView.findViewById(R.id.title_fav)
        var tvDetail: TextView = itemView.findViewById(R.id.locationdd)
    }
}