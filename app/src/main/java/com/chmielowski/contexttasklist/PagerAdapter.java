package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chmielowski.contexttasklist.view.TaskViewFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numberOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new TaskViewFragment();
        Bundle args = new Bundle();
        args.putInt("id", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
