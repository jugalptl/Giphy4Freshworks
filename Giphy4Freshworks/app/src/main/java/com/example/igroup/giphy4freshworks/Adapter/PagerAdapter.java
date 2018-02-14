package com.example.igroup.giphy4freshworks.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.igroup.giphy4freshworks.Activities.MainActivity;
import com.example.igroup.giphy4freshworks.Fragments.Fragment_Favorite_Gifs;
import com.example.igroup.giphy4freshworks.Fragments.Fragment_Trending_Gifs;
import com.example.igroup.giphy4freshworks.Pojo.Gif;

/**
 * Created by iGroup on 2/12/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numTab;
    MainActivity activity;
    public PagerAdapter(FragmentManager fragmentManager, int tab_no, MainActivity mainActivity)
    {
        super(fragmentManager);
        numTab = tab_no;
        activity = mainActivity;

    }
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                /*fragmetn*/
                Fragment_Trending_Gifs f1 = Fragment_Trending_Gifs.newInstance(activity);
                return f1;
            case 1:
                Fragment_Favorite_Gifs f2 = new Fragment_Favorite_Gifs();
                return f2;

             default:
                 return  null;
        }

    }

    @Override
    public int getCount() {
        return numTab;
    }
}
