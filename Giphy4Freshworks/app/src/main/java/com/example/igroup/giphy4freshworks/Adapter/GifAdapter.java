package com.example.igroup.giphy4freshworks.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.igroup.giphy4freshworks.Activities.MainActivity;
import com.example.igroup.giphy4freshworks.Pojo.Data;
import com.example.igroup.giphy4freshworks.R;

import java.util.List;

/**
 * Created by iGroup on 2/9/2018.
 */

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifViewHolder> {

    private List<Data> items ;
    private MainActivity context;

    public GifAdapter(MainActivity mainActivity) {
    context =mainActivity;
    }

    public void updateGifs(List<Data> gifs)
    {
        items = gifs;
        notifyDataSetChanged();
    }


    @Override
    public GifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new GifViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gif_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(GifViewHolder holder, int position) {
        Glide.with(context).load(items.get(position).getImages().getPreviewGif().getUrl()).into(holder.img_gif);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class GifViewHolder extends RecyclerView.ViewHolder {


        public ImageView img_gif;
        private Context contxt;

        public GifViewHolder(View itemView) {
            super(itemView);
        img_gif = (ImageView)itemView.findViewById(R.id.img_gif);
        }
    }
}
