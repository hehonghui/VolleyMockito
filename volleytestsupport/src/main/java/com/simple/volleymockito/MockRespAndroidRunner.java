package com.simple.volleymockito;


import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import com.simple.volletrestsupport.MockHttpStack;


/**
 * Created by mrsimple on 10/2/17.
 */
public class MockRespAndroidRunner extends AndroidJUnitRunner {

    protected MockHttpStack mMockStack;
    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        mMockStack = createMockHttpStack() ;
        VolleyQueue.setMockHttpStack(mMockStack);
        if ( mMockStack != null ) {
            configStack();
        }
    }

    protected MockHttpStack createMockHttpStack() {
        return new MockHttpStack() ;
    }

    protected void configStack() {}
}
