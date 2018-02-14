package com.example.igroup.giphy4freshworks.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.igroup.giphy4freshworks.Activities.MainActivity;
import com.example.igroup.giphy4freshworks.Adapter.GifAdapter;
import com.example.igroup.giphy4freshworks.Extra.MySingleton;
import com.example.igroup.giphy4freshworks.Pojo.Data;
import com.example.igroup.giphy4freshworks.Pojo.Gif;
import com.example.igroup.giphy4freshworks.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by iGroup on 2/12/2018.
 */

public class Fragment_Trending_Gifs extends Fragment{

   static MainActivity activity;
    RecyclerView rcv_gifs;
    GifAdapter gifAdapter;
    RecyclerView.LayoutManager layoutManager;
    SearchView searchView;
    Gif gif;
    List<Data> gifList;

public static Fragment_Trending_Gifs newInstance(MainActivity mainActivity)
{
    activity = mainActivity;

    Fragment_Trending_Gifs fragment =  new Fragment_Trending_Gifs();

return fragment;
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_trending_gifs,container,false);
        setHasOptionsMenu(true);
        rcv_gifs =(RecyclerView)view.findViewById(R.id.rcv_gifs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_gifs.setLayoutManager(linearLayoutManager);
        rcv_gifs.setHasFixedSize(true);
        gifList = new ArrayList<>();
        gifAdapter = new GifAdapter(activity,gifList);
        rcv_gifs.setAdapter(gifAdapter);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.giphy.com/v1/gifs/trending",null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null)
                {
                     gif = new Gson().fromJson(response.toString(),Gif.class);
                    if(gif.getData() != null) {
                        if (gifList != null){
                            gifList.clear();
                            gifList.addAll(gif.getData());
                        }
                        else
                        {
                            gifList.addAll(gif.getData());
                        }


                        //gifAdapter.updateGifs(gif.getData());
                        System.out.println("RESPONSE:" + gif.getData().get(0).getImages().getPreviewGif().getUrl());
                        //rcv_gifs.setAdapter(gifAdapter);
                        gifAdapter.notifyDataSetChanged();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(activity, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("api_key","6sZ6dmxYjYgP3Ji85pfk12GiRC8vWPP7");
                return headers;
            }
        };

        MySingleton.getInstance(activity).addTorequestqueue(jsonObjectRequest);

    return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu,inflater);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Data> filteredDataList = filter(gifList, newText);

                gifAdapter.setFilter(filteredDataList);
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {

                        gifAdapter.setFilter(gifList);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        gifAdapter.setFilter(gifList);
                        return true;
                    }
                });
}

    private List<Data> filter(List<Data> gifs, String query) {
        query = query.toLowerCase();
        final List<Data> filteredDataList = new ArrayList<>();
        for (Data model : gifs) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredDataList.add(model);
            }
        }
        return filteredDataList;
    }

    public interface OnFavGifPassedListener
    {
        void passGif(List<Data> favgifs);
    }
}
