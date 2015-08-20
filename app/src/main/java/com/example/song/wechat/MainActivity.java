package com.example.song.wechat;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class MainActivity extends FragmentActivity {

    private ImageView mTabline;
    private ViewPager mViewPager;
    private List<Fragment> mDatas;
    private FragmentPagerAdapter mAdapter;
    private TextView mTvChat;
    private TextView mTvFind;
    private TextView mTvContact;
    private int mScreenWidth1_3;
    private int mCurrentIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabLine();
        initView();
    }

    public void initTabLine() {
        mTabline = (ImageView) findViewById(R.id.tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        mScreenWidth1_3 = metrics.widthPixels / 3;
        lp.width = mScreenWidth1_3;
        mTabline.setLayoutParams(lp);
    }

    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTvChat = (TextView) findViewById(R.id.tv_chat);
        mTvFind = (TextView) findViewById(R.id.tv_find);
        mTvContact = (TextView) findViewById(R.id.tv_contact);

        ChatFragment chatFragment = new ChatFragment();
        ContactFragment contactFragment = new ContactFragment();
        FindFragment findFragment = new FindFragment();

        mDatas = new ArrayList<Fragment>();
        mDatas.add(chatFragment);
        mDatas.add(findFragment);
        mDatas.add(contactFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mDatas.get(position);
            }

            @Override
            public int getCount() {
                return mDatas.size();
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams llp = (LinearLayout.LayoutParams) mTabline.getLayoutParams();

                if (mCurrentIndex == 0 && position == 0) {
                    llp.leftMargin = (int) (mCurrentIndex * mScreenWidth1_3 + positionOffset * mScreenWidth1_3);
                } else if (mCurrentIndex == 1 && position == 0) {
                    llp.leftMargin = (int) (mCurrentIndex * mScreenWidth1_3 + (positionOffset - 1) * mScreenWidth1_3);
                } else if (mCurrentIndex == 1 && position == 1) {
                    llp.leftMargin = (int) (mCurrentIndex * mScreenWidth1_3 + positionOffset * mScreenWidth1_3);
                } else if (mCurrentIndex == 2 && position == 1) {
                    llp.leftMargin = (int) (mCurrentIndex * mScreenWidth1_3 + (positionOffset - 1) * mScreenWidth1_3);
                }

                mTabline.setLayoutParams(llp);
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position;

                mTvContact.setTextColor(Color.parseColor("#000000"));
                mTvChat.setTextColor(Color.parseColor("#000000"));
                mTvFind.setTextColor(Color.parseColor("#000000"));

                switch (position) {
                    case 0:
                        mTvChat.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 1:
                        mTvFind.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 2:
                        mTvContact.setTextColor(Color.parseColor("#008000"));
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




}

