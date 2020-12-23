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
import com.stigma15.pandu.MyData.MyDataPopular
import com.stigma15.pandu.R

class CardViewPopularHomeAdapter(private val listMyDatas: ArrayList<MyDataPopular>) : RecyclerView.Adapter<CardViewPopularHomeAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_dest, parent, false)
        return CardViewViewHolder(view)
    }
    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val myData = listMyDatas[position]
        Glide.with(holder.itemView.context)
                .load(myData.photo)
                .apply(RequestOptions().override(350, 550))
                .into(holder.imgPhoto)
        holder.tvName.text = myData.name
        holder.tvDetail.text = myData.location
        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context, "Kamu memilih " +
                listMyDatas[holder.adapterPosition].name, Toast.LENGTH_SHORT).show() }
    }
    override fun getItemCount(): Int {
        return listMyDatas.size
    }
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.item_popular)
        var tvName: TextView = itemView.findViewById(R.id.titledest)
        var tvDetail: TextView = itemView.findViewById(R.id.locationd)
    }
}