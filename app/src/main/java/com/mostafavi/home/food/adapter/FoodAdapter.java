package com.mostafavi.home.food.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mostafavi.home.food.Data.Food;
import com.mostafavi.home.food.R;
import com.mostafavi.home.food.interfaces.ListItemClickListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Reza on 2/2/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.Holder> {

    private Context mContext;
    private List<Food> foods;
    private ListItemClickListener itemClickListener;

    public FoodAdapter(Context mContext, List<Food> foods) {
        this.mContext = mContext;
        this.foods = foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_lst_food, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int pos) {
        final int position = holder.getAdapterPosition();
        Food food = foods.get(position);
        holder.tvDescription.setText(food.getDescription());
        holder.tvUserName.setText(food.getUser().getName());
        Picasso.with(mContext).load(food.getUser().getProfilePicture()).into(holder.imgProfile);
        Picasso.with(mContext).load(food.getImage()).into(holder.imgImage);
        holder.tvDate.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK).format(new Date(food.getDateTime())));
        holder.tvLike.setText(String.valueOf(food.getLike()));
        holder.panelMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods == null ? 0 : foods.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tvDescription, tvLike, tvUserName, tvDate;
        ImageView imgImage, imgProfile;
        View panelMain;

        public Holder(View itemView) {
            super(itemView);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvLike = (TextView) itemView.findViewById(R.id.tvLike);
            tvUserName = (TextView) itemView.findViewById(R.id.tvName);
            imgImage = (ImageView) itemView.findViewById(R.id.imgImage);
            imgProfile = (ImageView) itemView.findViewById(R.id.imgProfile);
            panelMain = itemView.findViewById(R.id.panelMain);
        }
    }
}
