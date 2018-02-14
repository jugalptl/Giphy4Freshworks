package com.example.igroup.giphy4freshworks.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.igroup.giphy4freshworks.Activities.MainActivity;
import com.example.igroup.giphy4freshworks.Extra.FavoriteGif;
import com.example.igroup.giphy4freshworks.Pojo.Data;
import com.example.igroup.giphy4freshworks.R;

import java.util.ArrayList;
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

        if (favitems != null) {
            Glide.with(holder.contxt).load(favitems.get(position).getImages().getPreviewGif().getUrl()).placeholder(R.drawable.ic_original).error(R.drawable.ic_flash).into(holder.img_fav_gif);
            System.out.println("fav"+favitems.get(position).getImages().getPreviewGif().getUrl().toString());
        }
    }
    public void setFavorite(FavoriteGif favoriteGif, MainActivity activity) {
        context = activity;
        favitems = new ArrayList<>();
        favitems.addAll(favoriteGif.getFav_gifs());
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return favitems == null ? 0 :favitems.size();
   //return 2;
    }


    public class FavoriteGifViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_fav_gif;
        public Context contxt;

        public FavoriteGifViewHolder(View itemView) {
            super(itemView);
            img_fav_gif = (ImageView)itemView.findViewById(R.id.img_fav_gif);
            contxt = itemView.getContext();
        }
    }
}