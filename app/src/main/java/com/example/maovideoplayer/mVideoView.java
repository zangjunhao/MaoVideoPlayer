package com.example.maovideoplayer;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by 67698 on 2018/5/20.
 */

public class mVideoView extends VideoView {
    public mVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }


    public mVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    public mVideoView(Context context) {
        super(context);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }


}
