package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chmielowski.contexttasklist.view.TaskViewFragment;

import java.util.List;

public final class PagerAdapter extends FragmentStatePagerAdapter {
    private final int numberOfTabs;
    private final List<Integer> listIndexes;

    public PagerAdapter(final FragmentManager fm,
                        final List<Integer> listIdxs) {
        super(fm);
        this.numberOfTabs = listIdxs.size();
        this.listIndexes = listIdxs;
    }

    @Override
    public Fragment getItem(final int tabNumber) {
        Fragment fragment = new TaskViewFragment();
        Bundle args = new Bundle();
        args.putInt("id", listIndexes.get(tabNumber));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
