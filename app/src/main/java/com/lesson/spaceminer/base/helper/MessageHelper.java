package com.lesson.spaceminer.base.helper;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

/**
 * All notice user related function declare here
 * Created by spaceminer on 25/10/2022.
 */

public class MessageHelper {

    Activity activity;

    public MessageHelper(Activity activity) {
        this.activity = activity;
    }

    /**
     * Toast message short duration
     *
     * @param msg
     */
    public void toastShort(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast message long duration
     *
     * @param msg
     */
    public void toastLong(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * Toast message short duration with y offset
     *
     * @param msg
     * @param yOffSet
     */
    public void toastShortWithGravity(String msg, int yOffSet) {
        Toast toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, yOffSet);
        toast.show();
    }


    /**
     * Toast message long duration reverse
     *
     * @param msg
     */
    public void toastLongWithGravity(String msg, int yOffset) {
        Toast toast = Toast.makeText(activity, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, yOffset);
        toast.show();
    }

    /**
     * Snack message short duration
     *
     * @param msg
     */
    public void snackShort(String msg, int layout) {
        Snackbar.make(activity.findViewById(layout), msg, Snackbar.LENGTH_LONG).show();
    }


}
