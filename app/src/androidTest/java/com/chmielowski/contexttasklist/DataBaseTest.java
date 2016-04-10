package com.chmielowski.contexttasklist;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class DataBaseTest extends AndroidTestCase {

    @Test
    public void testAppTitle() {
        assertEquals("Context Tasklist", getContext().getResources().getString(R.string.app_name));
    }
}
