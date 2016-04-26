package com.chmielowski.contexttasklist;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.chmielowski.contexttasklist.sql.SqlPersistence;
import com.chmielowski.contexttasklist.sql.SqlTaskList;
import com.chmielowski.contexttasklist.view.ListsView;
import com.chmielowski.contexttasklist.view.dialog.AddListDialog;
import com.chmielowski.contexttasklist.view.PagerAdapter;

import java.util.List;


public final class MainActivity extends AppCompatActivity implements ListsView {

    private final Persistence listsDataBase = new SqlPersistence(this, "Lists");

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_add_list) {
            final TabLayout tabs = (TabLayout) findViewById(R.id.tab_layout);
            new AddListDialog(this, this, tabs).show();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        try {
            List<Integer> listIndexes = listIndexes();
            for (int listIdx : listIndexes) {
                TaskList list = new SqlTaskList(listIdx,
                        new SqlPersistence(this, "Tasks"),
                        new SqlPersistence(this, "Lists"));
                list.showOn((ListsView)this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Integer> listIndexes() throws Exception {
        return this.listsDataBase.integers("id", "");
    }

    private void addTab(final String text) throws Exception {
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(
                tabLayout
                        .newTab()
                        .setText(text));
        inflateViewPager(tabLayout, listIndexes());
    }

    @Override
    public void showList(final String name) throws Exception {
        this.addTab(name);
    }

    private void inflateViewPager(TabLayout tabLayout, List<Integer> listIndexes) {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter(
                getSupportFragmentManager(),
                listIndexes));
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
