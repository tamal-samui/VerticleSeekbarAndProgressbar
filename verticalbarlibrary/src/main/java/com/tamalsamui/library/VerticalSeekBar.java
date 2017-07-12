package com.tamalsamui.library;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Tamal samui on 6/11/2016.
 */

public class VerticalSeekBar extends VerticalBar {

    public VerticalSeekBar(Context context) {
        super(context);
        init();
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        showThumb(true);
        setTouchListenerEnabled(true);
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener listener){
        super.setOnSeekBarChangeListener(listener);
    }
}