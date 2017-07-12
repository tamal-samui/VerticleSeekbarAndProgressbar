package com.tamalsamui.library;

/**
 * Created by Tamal samui on 7/12/2017.
 */

public interface OnSeekBarChangeListener {
    void onStartTrackingTouch(double progressInPercent);
    void onStopTrackingTouch(double progressInPercent);
    void onProgressChange(double progressInPercent);
}
