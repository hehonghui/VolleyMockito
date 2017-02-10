package com.simple.volletrestsupport;

import com.android.volley.Request;

/**
 * 模拟Volley的返回值
 * Created by mrsimple on 5/1/17.
 */
public class MockResponse {

    public static final int SUCCESS = 200 ;

    String subUrl = "";
    int method = Request.Method.GET;
    String body = "";
    String reason = "";
    int stCode = SUCCESS;
    String charset = "utf-8";


    public MockResponse setBody(String body) {
        this.body = body;
        return this;
    }

    public MockResponse setErrReason(String reason) {
        this.reason = reason;
        return this;
    }

    public MockResponse setStCode(int stCode) {
        this.stCode = stCode;
        return this;
    }


    public MockResponse setCharset(String charset) {
        this.charset = charset;
        return this;
    }


    public static MockResponse forRequest(String subUrl) {
        return forRequest(subUrl, Request.Method.GET);
    }


    /**
     *
     * http://www.myserver.com/v1/login, 这里的host为 http://www.myserver.com/, 那么 subUrl为 v1/login.
     * @param subUrl 请求的子路径
     * @param method 请求方法
     * @return
     */
    public static MockResponse forRequest(String subUrl, int method) {
        MockResponse response = new MockResponse();
        response.subUrl = subUrl;
        response.method = method ;
        return response;
    }


    public boolean match(Request request) {
        final String originUrl = request.getOriginUrl() ;
        return originUrl.endsWith(subUrl) && request.getMethod() == method ;
    }
}
