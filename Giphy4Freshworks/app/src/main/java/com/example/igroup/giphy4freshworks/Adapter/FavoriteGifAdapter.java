package com.example.igroup.giphy4freshworks.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igroup.giphy4freshworks.Activities.MainActivity;
import com.example.igroup.giphy4freshworks.Pojo.Data;
import com.example.igroup.giphy4freshworks.R;

import java.util.List;

/**
 * Created by iGroup on 2/13/2018.
 */

public class FavoriteGifAdapter extends RecyclerView.Adapter<FavoriteGifAdapter.FavoriteGifViewHolder> {

    private List<Data> favitems ;
    private MainActivity context;


    public FavoriteGifAdapter(MainActivity activity, List<Data> favgifs)
    {
        this.favitems = favgifs;
        this.context = activity;
    }
    @Override
    public FavoriteGifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_gif_item_view,parent,false) ;

        return new FavoriteGifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteGifViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        //return favitems == null ? 0 :favitems.size();
   return 2;
    }

    public class FavoriteGifViewHolder extends RecyclerView.ViewHolder{
        public FavoriteGifViewHolder(View itemView) {
            super(itemView);
        }
    }
}