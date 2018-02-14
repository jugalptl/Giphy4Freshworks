package com.example.igroup.giphy4freshworks.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.igroup.giphy4freshworks.Activities.MainActivity;
import com.example.igroup.giphy4freshworks.Extra.FavoriteGif;
import com.example.igroup.giphy4freshworks.Pojo.Data;
import com.example.igroup.giphy4freshworks.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iGroup on 2/9/2018.
 */

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifViewHolder>  {

    private List<Data> items,itemsFiltered,favGifs ;
    private MainActivity context;

    public GifAdapter(MainActivity mainActivity, List<Data> gifList) {
    context =mainActivity;
    this.itemsFiltered=gifList;
    this.items = gifList;
    favGifs = new ArrayList<>();
    }




    @Override
    public GifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif_item_view,parent,false) ;
        //GifViewHolder gifViewHolder = new GifViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gif_item_view,parent,false));
        return new GifViewHolder(view);

    }

    @Override
    public void onBindViewHolder(GifViewHolder holder, final int position) {
        holder.txt_gif_name.setText(items.get(position).getTitle());
        Glide.with(context).load(items.get(position).getImages().getPreviewGif().getUrl()).placeholder(R.drawable.ic_original).error(R.drawable.ic_flash).into(holder.img_gif);

        holder.chkbox_favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    favGifs.add(items.get(position));
                    System.out.println("favgif"+favGifs.toString());
                    EventBus.getDefault().post(new FavoriteGif(favGifs));
                }
            }

        });


    }
    @Override
    public int getItemCount() {
        return items == null ? 0: items.size();
    }



    public void setFilter(List<Data> itemsFiltered) {
        items = new ArrayList<>();
        items.addAll(itemsFiltered);
        notifyDataSetChanged();
    }

    public static class GifViewHolder extends RecyclerView.ViewHolder {


        public ImageView img_gif;
        private Context contxt;
        public TextView txt_gif_name;
        public CheckBox chkbox_favorite;

        public GifViewHolder(View itemView) {
            super(itemView);
        img_gif = (ImageView)itemView.findViewById(R.id.img_gif);
        txt_gif_name=(TextView)itemView.findViewById(R.id.txt_gifname);

        chkbox_favorite=(CheckBox)itemView.findViewById(R.id.checkBox_favorite);
        }
    }
}
