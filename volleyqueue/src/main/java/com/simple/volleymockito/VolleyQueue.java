package com.simple.volleymockito;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

/**
 * Created by mrsimple on 6/1/17.
 */

public final class VolleyQueue {

    private static HttpStack sStack = null;

    /**
     * 创建请求队列
     * @param context context
     * @param isDebug debug 模式下才允许使用 mock http stack
     * @return
     */
    public static RequestQueue create(Context context, boolean isDebug) {
        if ( sStack != null && isDebug) {
            Log.e("", "### use mock stack : " + sStack.getClass().getName());
            return Volley.newRequestQueue(context, sStack) ;
        }
        return Volley.newRequestQueue(context);
    }


    static void setMockHttpStack(HttpStack stack) {
        VolleyQueue.sStack = stack;
    }
}
