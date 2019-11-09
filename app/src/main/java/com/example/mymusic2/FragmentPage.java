package com.example.mymusic2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentPage extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    public FragmentPage(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);


    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public void AddFragment(Fragment fragment)
    {
        fragments.add(fragment);
    }
}
