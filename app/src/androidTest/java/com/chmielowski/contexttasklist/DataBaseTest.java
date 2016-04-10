package com.chmielowski.contexttasklist;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class DataBaseTest extends AndroidTestCase {

    @Test
    public void correctIntegers() {
        DataBase dataBase = new DataBase(getContext());

        try {
            List<Integer> result = dataBase.integers("id", "");
            assertEquals(Arrays.asList(1, 2), result);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void correctString() {
        DataBase dataBase = new DataBase(getContext());

        try {
            String result = dataBase.string("name", "id = 1");
            assertEquals("programming", result);
        } catch (Exception e) {
            fail();
        }
    }
}
