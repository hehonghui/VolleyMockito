package com.simple.mockvolleyserver.runner;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.simple.volletrestsupport.MockResponse;
import com.simple.volletrestsupport.utils.AssetsUtils;
import com.simple.volleymockito.MockRespAndroidRunner;

import org.json.JSONObject;

/**
 * Created by mrsimple on 6/1/17.
 */
public class MyAndroidRunner extends MockRespAndroidRunner {

    @Override
    protected void configStack() {
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
        mMockStack.mockResp(loginResp)
                .mockResp(MockResponse.forRequest("v1/news").setBody(jsonObject.toString()) ) ;
    }
}
