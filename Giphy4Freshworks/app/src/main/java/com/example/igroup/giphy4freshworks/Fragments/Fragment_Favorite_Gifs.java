package com.example.igroup.giphy4freshworks.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igroup.giphy4freshworks.Activities.MainActivity;
import com.example.igroup.giphy4freshworks.Adapter.FavoriteGifAdapter;
import com.example.igroup.giphy4freshworks.Extra.FavoriteGif;
import com.example.igroup.giphy4freshworks.Pojo.Data;
import com.example.igroup.giphy4freshworks.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iGroup on 2/12/2018.
 */

public class Fragment_Favorite_Gifs extends Fragment {

    RecyclerView rcv_fav_gifs;
    List<Data> favgifList;
    static MainActivity activity;
    FavoriteGifAdapter favoriteGifAdapter;

    public static Fragment_Favorite_Gifs newInstance(MainActivity mainActivity)
    {
        activity = mainActivity;

        Fragment_Favorite_Gifs fragment =  new Fragment_Favorite_Gifs();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_favorite_gifs,container,false);
        EventBus.getDefault().register(this);
        rcv_fav_gifs=(RecyclerView)view.findViewById(R.id.rcv_fav_gifs);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcv_fav_gifs.setLayoutManager(gridLayoutManager);
        rcv_fav_gifs.setHasFixedSize(true);
        favgifList = new ArrayList<>();
        favoriteGifAdapter = new FavoriteGifAdapter(activity,favgifList);
        rcv_fav_gifs.setAdapter(favoriteGifAdapter);

        return view;
    }

    @Subscribe
    public void onEvent(FavoriteGif favoriteGif){
        favoriteGifAdapter.setFavorite(favoriteGif,activity);
    }
}
