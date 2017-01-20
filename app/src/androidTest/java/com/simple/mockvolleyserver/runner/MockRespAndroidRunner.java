package com.simple.mockvolleyserver.runner;

import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnitRunner;
import android.util.Log;

import com.simple.volleymockito.AssetsUtils;
import com.simple.volleymockito.MockHttpStack;
import com.simple.volleymockito.MockResponse;
import com.simple.volleymockito.VolleyQueue;

import org.json.JSONObject;

/**
 * Created by mrsimple on 6/1/17.
 */
public class MockRespAndroidRunner extends AndroidJUnitRunner {

    MockHttpStack mStack = new MockHttpStack();

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        Log.e("", "### my runner create ");

        VolleyQueue.setMockHttpStack(mStack);
        configStack();
    }

    private void configStack() {
        JSONObject jsonObject = new JSONObject() ;
        try {
            jsonObject.put("data", "my_news");
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Context testContext = InstrumentationRegistry.getContext() ;
        String loginRespString = AssetsUtils.parseString(testContext, "login/login_resp.json") ;
        // mock 登录
        MockResponse loginResp = MockResponse.forRequest("v1/login/").setBody(loginRespString) ;

        // builder 形式的mock
        mStack.mockResp(loginResp)
                .mockResp(MockResponse.forRequest("v1/news").setBody(jsonObject.toString()) ) ;
    }
}
