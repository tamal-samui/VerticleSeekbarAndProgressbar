package com.tamalsamui.library;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Tamal samui on 6/27/2017.
 */

public class VerticalProgressBar extends VerticalBar {

    public VerticalProgressBar(Context context) {
        super(context);
        init();
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        showThumb(false);
        setTouchListenerEnabled(false);
    }
}