package com.example.igroup.giphy4freshworks.Activities;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.igroup.giphy4freshworks.Adapter.PagerAdapter;
import com.example.igroup.giphy4freshworks.Fragments.Fragment_Trending_Gifs;
import com.example.igroup.giphy4freshworks.Pojo.Data;
import com.example.igroup.giphy4freshworks.R;

import java.util.List;


public class MainActivity extends AppCompatActivity implements Fragment_Trending_Gifs.OnFavGifPassedListener{

    Toolbar toolbar;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Trending Gifs"));
        tabLayout.addTab(tabLayout.newTab().setText("Favorite Gifs"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter pageAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),MainActivity.this);
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


/*
        rcv_gifs =(RecyclerView)findViewById(R.id.rcv_gifs);
        rcv_gifs.setLayoutManager(new LinearLayoutManager(this));
        rcv_gifs.setHasFixedSize(true);
        gifAdapter = new GifAdapter(MainActivity.this);
*/


    }


    @Override
    public void passGif(List<Data> favgifs) {

    }
}

