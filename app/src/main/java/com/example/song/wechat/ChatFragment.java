package com.example.song.wechat;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ChatFragment extends Fragment {
    private ListView lv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getChatList();

        View lvrootview = inflater.inflate(R.layout.fragment_chat, null);
        lv = (ListView) lvrootview.findViewById(R.id.id_lv);
        ArrayList<HashMap<String, Object>> itemdata = new ArrayList<HashMap<String, Object>>();
        for (int i=0; i<20; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tv_chat_1", "hello i am "+i);
            map.put("tv_chat_2", "how are you!");
            itemdata.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this.getActivity().getApplicationContext(), itemdata, R.layout.list_item,
                new String[]{"tv_chat_1", "tv_chat_2"}, new int[]{R.id.tv_chat_1, R.id.tv_chat_2});
        lv.setAdapter(simpleAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("item", position);
                startActivity(intent);
            }
        });
        return lvrootview;
    }

    private void getChatList() {
        String url = "http://moryies.sinaapp.com/response.php";
        JsonObjectRequest jsRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("data", "begin get data");
                Log.i("data", response.toString());
                Toast.makeText(getActivity().getApplicationContext(), "get:"+response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApp.getRequestQueue().add(jsRequest);

    }

}
