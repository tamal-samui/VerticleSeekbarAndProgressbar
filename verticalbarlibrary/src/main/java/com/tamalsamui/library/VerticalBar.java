package com.tamalsamui.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Tamal samui on 11/10/2016.
 */

abstract class VerticalBar extends View {

    private float leftRightPadding = 0;
    private double mProgressPercent = 50;
    private Paint progressBarPaint, progressBackgroundPaint, thumbPaint;
    private boolean isTouchListenerEnabled = true;
    private boolean isUserTouched = false;
    private boolean showThumb = true;
    private OnSeekBarChangeListener mOnSeekBarChangeListener;

    private int PROGRESS_BAR_COLOR= Color.CYAN, PROGRESS_BACKGROUND_COLOR = Color.LTGRAY, THUMB_COLOR = Color.BLUE;

    public VerticalBar(Context context) {
        super(context);
        init();
    }

    public VerticalBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setProgressBarColor(int color){
        PROGRESS_BAR_COLOR = color;
        progressBarPaint.setColor(PROGRESS_BAR_COLOR);
    }

    public void setProgressBarBackgroundColor(int color){
        PROGRESS_BACKGROUND_COLOR = color;
        progressBackgroundPaint.setColor(PROGRESS_BACKGROUND_COLOR);
    }

    public void setThumbColor(int color){
        THUMB_COLOR = color;
        thumbPaint.setColor(THUMB_COLOR);
    }

    protected void showThumb(boolean bool){
        showThumb = bool;
    }

    public void setTouchListenerEnabled(boolean touchListenerEnabled) {
        isTouchListenerEnabled = touchListenerEnabled;
    }

    public void setProgress(double percent){
        if(percent < 0){
            percent = 0;
        } else if(percent > 100){
            percent = 100;
        }
        mProgressPercent = percent;
        invalidate();
        if(mOnSeekBarChangeListener != null){
            mOnSeekBarChangeListener.onProgressChange(mProgressPercent);
        }
    }

    protected void setOnSeekBarChangeListener(OnSeekBarChangeListener listener){
        mOnSeekBarChangeListener = listener;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VerticalBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        progressBarPaint = new Paint();
        progressBarPaint.setStyle(Paint.Style.FILL);
        progressBarPaint.setColor(PROGRESS_BAR_COLOR);

        progressBackgroundPaint = new Paint();
        progressBackgroundPaint.setStyle(Paint.Style.FILL);
        progressBackgroundPaint.setColor(PROGRESS_BACKGROUND_COLOR);

        thumbPaint = new Paint();
        thumbPaint.setStyle(Paint.Style.FILL);
        thumbPaint.setColor(THUMB_COLOR);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float bar_width = getWidth() / 6;

        float bar_height = getHeight();
        leftRightPadding = (getWidth() - bar_width) / 2;

        //draw starting arc
        float arcShapeRadius = bar_width / 2;
        canvas.drawCircle((arcShapeRadius + leftRightPadding), arcShapeRadius, arcShapeRadius, progressBarPaint);

        //draw progress area
        float progress = bar_height * (float) mProgressPercent / 100;
        float progressBottom = progress;
        if (progressBottom == bar_height) {
            progressBottom = bar_height - arcShapeRadius;
        }
        canvas.drawRect(leftRightPadding, arcShapeRadius, (bar_width + leftRightPadding), progressBottom, progressBarPaint);

        //draw background area
        float backgroundTop = progress; // default
        if (backgroundTop == 0) {
            backgroundTop = arcShapeRadius;
        }
        canvas.drawRect(leftRightPadding, backgroundTop, (bar_width + leftRightPadding), bar_height - arcShapeRadius, progressBackgroundPaint);

        //draw ending arc
        canvas.drawCircle((arcShapeRadius + leftRightPadding), bar_height - arcShapeRadius, arcShapeRadius, progressBackgroundPaint);

        //draw progress thumb
        if(showThumb) {
            float thumbRadius = 0;
            if (isUserTouched) {
                thumbRadius = getWidth() / 2;
            } else {
                thumbRadius = getWidth() / 4;
            }
            float dyCircle = progress; // default
            if (dyCircle < thumbRadius) {
                dyCircle = thumbRadius;
            } else if (dyCircle > (bar_height - thumbRadius)) {
                dyCircle = bar_height - thumbRadius;
            }
            Log.d("Tamal", "dyCircle = " + String.valueOf(dyCircle) + " progress =" + String.valueOf(progress) +
                    " mProgressPercent=" + mProgressPercent);
            canvas.drawCircle((arcShapeRadius + leftRightPadding), dyCircle, thumbRadius, thumbPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isTouchListenerEnabled) {
            return false;
        }

        int eventAction = event.getAction();

        // you may need the x/y location
        double x = (double) event.getX();
        double y = (double) event.getY();

        double canvas_height = getHeight();
        if (y < 0) {
            y = 0;
        } else if (y > canvas_height) {
            y = canvas_height;
        }

        //handle the event
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                isUserTouched = true;
                mProgressPercent = y / canvas_height * 100;
                invalidate();
                if(mOnSeekBarChangeListener != null){
                    mOnSeekBarChangeListener.onStartTrackingTouch(mProgressPercent);
                }
                break;
            case MotionEvent.ACTION_UP:
                isUserTouched = false;
                mProgressPercent = y / canvas_height * 100;
                invalidate();
                if(mOnSeekBarChangeListener != null){
                    mOnSeekBarChangeListener.onStopTrackingTouch(mProgressPercent);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mProgressPercent = y / canvas_height * 100;
                invalidate();
                if(mOnSeekBarChangeListener != null){
                    mOnSeekBarChangeListener.onProgressChange(mProgressPercent);
                }
                break;
        }
        return true;
    }
}