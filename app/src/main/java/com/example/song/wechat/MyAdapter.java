package com.example.song.wechat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by song on 2015/12/5.
 */
public class MyAdapter extends BaseAdapter {

    List<DataBean> mDataBeanList;
    LayoutInflater mInflater;
    public MyAdapter(Context context, List<DataBean> dataBeanList) {
        mInflater = LayoutInflater.from(context);
        this.mDataBeanList = dataBeanList;
    }

    @Override
    public int getCount() {
        return mDataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, null);
            viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_chat_1);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chat_2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DataBean dataBean = mDataBeanList.get(position);
        viewHolder.iconImg.setImageResource(R.mipmap.actionbar_add_icon);
        viewHolder.tvName.setText(dataBean.getmName());
        viewHolder.tvContent.setText(dataBean.getmAge());
        return convertView;
    }

    public class ViewHolder {
        public ImageView iconImg;
        public TextView tvName;
        public TextView tvContent;
    }
}
