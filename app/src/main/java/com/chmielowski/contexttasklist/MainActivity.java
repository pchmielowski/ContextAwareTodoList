package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chmielowski.contexttasklist.sql.DataBase;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        Persistence listsDataBase = new DataBase(this, "Lists");
        try {
            List<Integer> listIndexes = listsDataBase.integers("id", "");
            for (int listIdx : listIndexes) {
                tabLayout.addTab(
                        tabLayout.newTab()
                                .setText(listsDataBase.string("name", "id=" + listIdx)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Button addTaskButton = (Button) findViewById(R.id.add_list_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                tabLayout.addTab(tabLayout.newTab());
                inflateViewPager(tabLayout);
            }
        });

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
