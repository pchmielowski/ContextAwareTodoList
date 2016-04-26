package com.chmielowski.contexttasklist;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import com.chmielowski.contexttasklist.sql.SqlPersistence;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;


@RunWith(AndroidJUnit4.class)
public class SqlPersistenceTest extends AndroidTestCase {

    private static final String TABLE_NAME = "test";

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
        sql.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        sql.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME +
                " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "strings TEXT, " +
                "booleans INTEGER" +
                ");");
        sql.getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME
                + " (strings, booleans) VALUES ('first', 0);");
        sql.getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME
                + " (strings, booleans) VALUES ('second', 1);");
        sql.getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME
                + " (strings, booleans) VALUES ('third', 1);");
    }

    @MediumTest
    public void returns_correct_integers() throws Exception {
        SqlPersistence dataBase = new SqlPersistence(getContext(), TABLE_NAME);

        List<Integer> result = dataBase.integers("id", "");

        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @MediumTest
    public void returns_correct_strings() throws Exception {
        SqlPersistence dataBase = new SqlPersistence(getContext(), TABLE_NAME);

        String result1 = dataBase.string("strings", "id = 1");
        String result2 = dataBase.string("strings", "id = 2");

        assertEquals("first", result1);
        assertEquals("second", result2);
    }

    @MediumTest
    public void returns_correct_booleans() throws Exception {
        SqlPersistence dataBase = new SqlPersistence(getContext(), TABLE_NAME);

        boolean result1 = dataBase.bool("booleans", "id = 1");
        boolean result2 = dataBase.bool("booleans", "id = 2");

        assertEquals(false, result1);
        assertEquals(true, result2);
    }

    @MediumTest
    public void throws_exception_if_wrong_argument_for_bool() throws Exception {
        SqlPersistence dataBase = new SqlPersistence(getContext(), TABLE_NAME);
        try {
            dataBase.bool("booleans", "");
            Assert.fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // success
        } catch (Exception e) {
            throw e;
        }
    }
}
