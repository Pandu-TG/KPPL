package com.stigma15.pandu.Adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.stigma15.pandu.Activity.OverviewActivity
import com.stigma15.pandu.MyData.MyDataRecomendation
import com.stigma15.pandu.R

class SliderHomeAdapter(private val DataList: ArrayList<MyDataRecomendation>) : RecyclerView.Adapter<SliderHomeAdapter.SliderHomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderHomeAdapter.SliderHomeViewHolder {
        val viewd: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recomendation_page, parent, false)
        return SliderHomeViewHolder(viewd)
    }
    override fun onBindViewHolder(holder: SliderHomeViewHolder, position: Int) {
        val myData = DataList[position]
        Glide.with(holder.itemView.context)
                .load(myData.image)
                .apply(RequestOptions().override(350, 550))
                .into(holder.imgPhoto)
        holder.tvName.text = myData.title
        holder.tvLoc.text = myData.locationd
        holder.itemView.setOnClickListener {val context=holder.itemView.context
            val intent = Intent(context, OverviewActivity::class.java)
            context.startActivity(intent)
            }
        }

    override fun getItemCount(): Int {
        return DataList.size
    }
    inner class SliderHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.rec_img)
        var tvName: TextView = itemView.findViewById(R.id.titleTv)
        var tvLoc: TextView = itemView.findViewById(R.id.locTv)
    }
}
