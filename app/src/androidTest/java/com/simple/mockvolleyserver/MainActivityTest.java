package com.simple.mockvolleyserver;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;

/**
 * 启动 MainActivity
 * Created by mrsimple on 5/1/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends Assert {

    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule<>(MainActivity.class) ;

    @Test
    public void testMain() throws Exception {
        sleep(1000 * 8);
    }
}
