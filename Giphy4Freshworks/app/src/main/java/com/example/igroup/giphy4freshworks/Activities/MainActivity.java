package com.example.igroup.giphy4freshworks.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.igroup.giphy4freshworks.Adapter.GifAdapter;
import com.example.igroup.giphy4freshworks.Extra.MySingleton;
import com.example.igroup.giphy4freshworks.Pojo.Data;
import com.example.igroup.giphy4freshworks.Pojo.Gif;
import com.example.igroup.giphy4freshworks.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rcv_gifs;
    GifAdapter gifAdapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rcv_gifs =(RecyclerView)findViewById(R.id.rcv_gifs);
        rcv_gifs.setLayoutManager(new LinearLayoutManager(this));
        rcv_gifs.setHasFixedSize(true);
        gifAdapter = new GifAdapter(MainActivity.this);
        rcv_gifs.setAdapter(gifAdapter);

        /*JSONObject params = new JSONObject();
        try {
            params.put("api_key","6sZ6dmxYjYgP3Ji85pfk12GiRC8vWPP7");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.giphy.com/v1/gifs/trending",null,new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
            if (response != null)
            {
                Gif gif = new Gson().fromJson(response.toString(),Gif.class);
                gifAdapter.updateGifs(gif.getData());
                System.out.println("RESPONSE:"+gif.getData().get(0).getImages().getPreviewGif().getUrl());

                /*JsonParser jsonParser = new JsonParser();
                JsonArray results = null;
                try {
                    results = jsonParser.parse(response)
                            .getAsJsonObject().get("Data")
                            .getAsJsonObject().getAsJsonArray("View").get(0)
                            .getAsJsonObject().getAsJsonArray("Result");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (JsonElement result : results) {
                    JsonObject address = result.getAsJsonObject().get("Location").getAsJsonObject().getAsJsonObject("Address");
                    String postalCode = address.get("PostalCode").getAsString();
                    System.out.println(postalCode);
                }*/

                //gifAdapter.updateGifs(response.getData().get(0).getImages().getPreviewGif().getUrl());
                //System.out.println(response.getData().get(0).getImages().getPreviewGif().getUrl());
            }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("api_key","6sZ6dmxYjYgP3Ji85pfk12GiRC8vWPP7");
                                return headers;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addTorequestqueue(jsonObjectRequest);





    }
}
