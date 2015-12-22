package com.example.song.wechat;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChatFragment extends Fragment {
    private ListView lv;
    private List<DataBean> dataBeanList;
    private Handler handler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dataBeanList = new ArrayList<>();
        getChatList();

        View lvrootview = inflater.inflate(R.layout.fragment_chat, null);
        lv = (ListView) lvrootview.findViewById(R.id.id_lv);
        ArrayList<HashMap<String, Object>> itemdata = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 20; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tv_chat_1", "hello i am " + i);
            map.put("tv_chat_2", "how are you!");
            itemdata.add(map);
        }

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
//                dataBeanList = (List<DataBean>) msg.obj;
                lv.setAdapter(new MyAdapter(getActivity().getApplicationContext(),
                        (List<DataBean>) msg.obj));
            }
        };

//        SimpleAdapter simpleAdapter = new SimpleAdapter(this.getActivity().getApplicationContext(),
//                itemdata, R.layout.list_item, new String[]{"tv_chat_1", "tv_chat_2"},
//                new int[]{R.id.tv_chat_1, R.id.tv_chat_2});
//        lv.setAdapter(new MyAdapter(getActivity().getApplicationContext(), dataBeanList));
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
                try {
                    DataBean dataBean;
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        dataBean = new DataBean();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataBean.setmImgUrl(jsonObject.getString("imageurl"));
                        dataBean.setmAge(jsonObject.getInt("age")+"");
                        dataBean.setmName(jsonObject.getString("name"));
                        dataBeanList.add(dataBean);
                    }

                    Message message = Message.obtain(handler);
                    message.obj = dataBeanList;
                    message.sendToTarget();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(getActivity().getApplicationContext(), "get:"+response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApp.getRequestQueue().add(jsRequest);

    }

}
