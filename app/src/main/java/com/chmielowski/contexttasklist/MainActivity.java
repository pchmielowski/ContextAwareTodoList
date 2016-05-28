package com.chmielowski.contexttasklist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chmielowski.contexttasklist.commands.RemoveCurrentTabCommand;
import com.chmielowski.contexttasklist.sql.SqlPersistence;
import com.chmielowski.contexttasklist.sql.SqlTaskList;
import com.chmielowski.contexttasklist.view.ListsView;
import com.chmielowski.contexttasklist.view.PagerAdapter;
import com.chmielowski.contexttasklist.view.dialog.AddListDialog;

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
            new AddListDialog(this, this, tabLayout()).show();
            return true;
        }
        if (menuItem.getItemId() == R.id.action_remove_list) {
            try {
                new RemoveCurrentTabCommand((ListsView) this).execute();
            } catch (Exception e) {
                e.printStackTrace();
                Context context = getApplicationContext();
                CharSequence text = "Can not remove list!";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void removeCurrentTab() throws Exception {
        final int tabNumber = this.tabLayout().getSelectedTabPosition();
        TaskList list = new SqlTaskList(this.listIndexes().get(tabNumber),
                new SqlPersistence(this, "Tasks"),
                new SqlPersistence(this, "Lists"));
        list.remove();
        this.tabLayout().removeTabAt(tabNumber);
        this.updateAdapter(tabLayout(), listIndexes());
    }

    private TabLayout tabLayout() {
        return (TabLayout) findViewById(R.id.tab_layout);
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        final Beacon beacon = new Beacon();

        this.createTasksTable();
        this.createListsTable();
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        try {
            this.showLists();
            this.setListeners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLists() throws Exception {
        List<Integer> listIndexes = listIndexes();
        for (int listIdx : listIndexes) {
            TaskList list = new SqlTaskList(listIdx,
                    new SqlPersistence(this, "Tasks"),
                    new SqlPersistence(this, "Lists"));
            list.showOn((ListsView) this);
        }
    }

    private void setListeners() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout()));
        tabLayout().setOnTabSelectedListener(
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

    private List<Integer> listIndexes() throws Exception {
        return this.listsDataBase.integers("id", "");
    }

    @Override
    public void addTaskList(final String name) throws Exception {
        final TabLayout tabLayout = tabLayout();
        final TabLayout.Tab tabWithoutName = tabLayout.newTab();
        final TabLayout.Tab tabWithName = tabWithoutName.setText(name);
        tabLayout.addTab(tabWithName);
        this.updateAdapter(tabLayout, listIndexes());
    }

    private void updateAdapter(final TabLayout tabLayout,
                               final List<Integer> listIndexes) {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter(
                getSupportFragmentManager(),
                listIndexes));
    }

    private void createTasksTable() {
        SQLiteOpenHelper sql = new SQLiteOpenHelper(this, "tasks.db", null, 1) {
            @Override
            public void onCreate(final SQLiteDatabase db) {
            }

            @Override
            public void onUpgrade(final SQLiteDatabase db,
                                  final int oldVersion,
                                  final int newVersion) {
            }
        };
        sql.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS "
                + "Tasks"
                + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT, "
                + "done INTEGER,"
                + "list INTEGER"
                + ");");
    }

    private void createListsTable() {
        SQLiteOpenHelper sql = new SQLiteOpenHelper(
                this, "tasks.db", null, 1) {
            @Override
            public void onCreate(final SQLiteDatabase db) {
            }

            @Override
            public void onUpgrade(final SQLiteDatabase db,
                                  final int oldVersion,
                                  final int newVersion) {
            }
        };
//        sql.getWritableDatabase().execSQL("DROP TABLE Lists;");
        sql.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS "
                + "Lists"
                + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT"
                + ");");
//        sql.getWritableDatabase().execSQL("INSERT INTO " + "Lists"
//                + " (name) VALUES ('first');");
//        sql.getWritableDatabase().execSQL("INSERT INTO " + "Lists"
//                + " (name) VALUES ('second');");
//        sql.getWritableDatabase().execSQL("INSERT INTO " + "Lists"
//                + " (name) VALUES ('third');");
    }

}
