package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.chmielowski.contexttasklist.sql.DataBase;

import java.util.List;


public final class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_add_list) {
            final TabLayout tabs = (TabLayout) findViewById(R.id.tab_layout);
            tabs.addTab(tabs.newTab());
            inflateViewPager(tabs);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        Persistence listsDataBase = new DataBase(this, "Lists");
        try {
            List<Integer> listIndexes = listsDataBase.integers("id", "");
            for (int listIdx : listIndexes) {
                tabLayout.addTab(
                        tabLayout
                                .newTab()
                                .setText(listsDataBase.string(
                                        "name",
                                        "id=" + listIdx)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inflateViewPager(tabLayout);
    }

    private void inflateViewPager(TabLayout tabLayout) {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter(
                getSupportFragmentManager(),
                tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(final TabLayout.Tab tab) {
                        int position = tab.getPosition();
                        viewPager.setCurrentItem(position);
                    }

                    @Override
                    public void onTabUnselected(final TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(final TabLayout.Tab tab) {

                    }
                });
    }
}
