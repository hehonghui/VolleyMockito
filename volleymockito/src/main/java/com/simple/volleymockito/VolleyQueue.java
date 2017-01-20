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

    public static RequestQueue create(Context context) {
        return create(context, false) ;
    }


    public static RequestQueue create(Context context, boolean useDefault) {
        if ( sStack != null && !useDefault) {
            Log.e("", "#### use custom stack : " + sStack.getClass().getName());
            return Volley.newRequestQueue(context, sStack) ;
        }
        Log.e("", "#### use default stack");
        return Volley.newRequestQueue(context);
    }


    public static void setMockHttpStack(HttpStack stack) {
        VolleyQueue.sStack = stack;
    }
}
