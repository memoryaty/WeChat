package com.example.song.wechat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Song on 2015/8/5.
 */
public class AudioButton extends Button {
    public AudioButton(Context context) {
        this(context, null);
    }

    public AudioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        return super.onTouchEvent(event);
    }
}
