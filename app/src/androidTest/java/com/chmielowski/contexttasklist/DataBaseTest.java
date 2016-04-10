package com.chmielowski.contexttasklist;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;


@RunWith(AndroidJUnit4.class)
public class DataBaseTest extends AndroidTestCase {

    private static final String DATABASE_NAME = "Tasks";

    @Before
    public void setUp() {
        SQLiteOpenHelper sql = new SQLiteOpenHelper(getContext(), "tasks.db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        };
        sql.getWritableDatabase().execSQL("DROP TABLE " + DATABASE_NAME + ";");
        sql.getWritableDatabase().execSQL("CREATE TABLE " + DATABASE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT," +
                "done INTEGER" +
                ");");
        sql.getWritableDatabase().execSQL("INSERT INTO Tasks (name, done) VALUES ('do shopping', 0);");
        sql.getWritableDatabase().execSQL("INSERT INTO Tasks (name, done) VALUES ('write a book', 1);");
    }

    @MediumTest
    public void returns_correct_integers() throws Exception {
        DataBase dataBase = new DataBase(getContext());

        List<Integer> result = dataBase.integers("id", "");

        assertEquals(Arrays.asList(1, 2), result);
    }

    @MediumTest
    public void returns_correct_strings() throws Exception {
        DataBase dataBase = new DataBase(getContext());

        String result1 = dataBase.string("name", "id = 1");
        String result2 = dataBase.string("name", "id = 2");

        assertEquals("do shopping", result1);
        assertEquals("write a book", result2);
    }

    @MediumTest
    public void returns_correct_booleans() throws Exception {
        DataBase dataBase = new DataBase(getContext());

        boolean result1 = dataBase.bool("done", "id = 1");
        boolean result2 = dataBase.bool("done", "id = 2");

        assertEquals(false, result1);
        assertEquals(true, result2);
    }
}
