package com.simple.volleymockito;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HurlStack;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mrsimple on 5/1/17.
 */

public class MockHttpStack extends HurlStack {

    private static final ProtocolVersion HTTP_1_1 = new ProtocolVersion("http", 1, 1) ;

    private static List<MockResponse> mMockResponses = new ArrayList<>();

    @Override
    public org.apache.http.HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders)
            throws IOException, AuthFailureError {
        // debug状态下才可以
        HttpResponse response = intercept(request);
        if (response != null) {
            return response;
        }
        return super.performRequest(request, additionalHeaders);
    }


    private HttpResponse intercept(Request<?> request) {
        for (MockResponse resp : mMockResponses) {
            if (resp.match(request)) {
                return createResponse(resp);
            }
        }
        return null;
    }


    private HttpResponse createResponse(MockResponse mockResp) {
        BasicHttpResponse response = new BasicHttpResponse(HTTP_1_1, mockResp.stCode,
                mockResp.reason);
        try {
            response.setEntity(new StringEntity(mockResp.body, mockResp.charset));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    /**
     * 添加要mock的response
     *
     * @param response
     * @return
     */
    public MockHttpStack mockResp(MockResponse response) {
        if (response != null) {
            this.mMockResponses.add(response);
        }
        return this;
    }
}
