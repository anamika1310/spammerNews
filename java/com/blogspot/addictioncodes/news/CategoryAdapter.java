package com.blogspot.addictioncodes.news;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hp on 7/18/2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {
    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new politicfrag();
        else if(position==1)
            return new sportsFrag();
        else if(position==2)
            return new mediaFrag();
        else if(position==3)
            return new technologyFrag();
        else return new economicFrag();
    }

    @Override
    public int getCount() {
        return 5;
    }
    @Override
    public String getPageTitle(int position){
        if(position==0)
            return "Politics";
        else if(position==1)
            return "Sports";
        else if(position==2)
            return "Media";
        else if(position==3)
            return "Technology";
        else return "Economical";
    }
}
