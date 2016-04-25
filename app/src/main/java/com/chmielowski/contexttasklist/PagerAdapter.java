package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chmielowski.contexttasklist.view.TaskViewFragment;

public final class PagerAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;

    public PagerAdapter(final FragmentManager fm, final int numTabs) {
        super(fm);
        this.numberOfTabs = numTabs;
    }

    @Override
    public Fragment getItem(final int tabNumber) {
        Fragment fragment = new TaskViewFragment();
        Bundle args = new Bundle();
        args.putInt("id", tabNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
