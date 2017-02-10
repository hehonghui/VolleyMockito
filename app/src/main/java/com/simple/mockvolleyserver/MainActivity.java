package com.simple.mockvolleyserver;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.simple.volleymockito.VolleyQueue;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Handler mHandler = new Handler(Looper.getMainLooper());
    RequestQueue mQueue;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.log_tv);
        mQueue = VolleyQueue.create(getApplicationContext(), BuildConfig.DEBUG);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "发起请求 ==> ", Toast.LENGTH_SHORT).show();
                testJsonRequest();
                testStringRequest();
            }
        }, 1000);
    }


    private void testStringRequest() {
        StringRequest request = new StringRequest(Request.Method.GET, "http://www.newsdog.today/v1/login/", new Response.Listener<String>() {
            @Override
            public void onResponse(final String s) {
                Toast.makeText(MainActivity.this, "result : " + s, Toast.LENGTH_SHORT).show();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.append("Login请求结果: " + s.toString() + "\n\n");
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "1 --> volleyError : " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);
    }


    private void testJsonRequest() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://www.json.today/v1/news", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject s) {
                Toast.makeText(MainActivity.this, "result : " + s, Toast.LENGTH_SHORT).show();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.append("新闻列表请求: " + s.toString() + "\n\n");
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "2 --> volleyError : " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);
    }

}
