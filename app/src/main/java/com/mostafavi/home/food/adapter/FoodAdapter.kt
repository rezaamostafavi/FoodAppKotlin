package com.mostafavi.home.food.adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mostafavi.home.food.Data.Food
import com.mostafavi.home.food.R
import com.mostafavi.home.food.interfaces.ListItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_lst_food.view.*
import java.text.SimpleDateFormat
import java.util.*

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.Holder> {

    var mContext: Context? = null
    var foods: ArrayList<Food>? = null
    private var itemClickListener:ListItemClickListener? = null

    constructor(mContext: Context?, foods: ArrayList<Food>?) {
        this.mContext = mContext
        this.foods = foods
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        return Holder(LayoutInflater.from(mContext).inflate(R.layout.item_lst_food, parent, false))
    }

    override fun getItemCount(): Int {
        return foods!!.size
    }

    override fun onBindViewHolder(holder: Holder?, pos: Int) {
        val position: Int = holder!!.adapterPosition
        val food = foods?.get(position)
        holder?.tvDescription?.text = food?.description
        holder?.tvName?.text = food?.user?.name
        holder?.tvLike?.text = food?.like.toString()
        holder?.tvDate?.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date(food?.dateTime!!))
        Picasso.with(mContext).load(food?.image).into(holder?.imgImage)
        Picasso.with(mContext).load(food?.user?.profilePicture).into(holder?.imgProfile)
        holder?.panelMain!!.setOnClickListener({
            itemClickListener!!.onItemClick(it, position)
        })
    }

    fun setItemClickListener(itemClickListener: ListItemClickListener){
        this.itemClickListener = itemClickListener
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvDescription = itemView?.tvDescription
        var tvDate = itemView?.tvDate
        var tvLike = itemView?.tvLike
        var tvName = itemView?.tvName
        var imgImage = itemView?.imgImage
        var imgProfile = itemView?.imgProfile
        var panelMain = itemView?.panelMain
    }

}