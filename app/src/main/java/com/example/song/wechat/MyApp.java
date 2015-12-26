package com.example.song.wechat;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by song on 2015/11/12.
 */
public class MyApp extends Application{
    public static RequestQueue requestQueue;
    @Override
    public void onCreate() {
        Fresco.initialize(this);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        super.onCreate();
    }
    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
