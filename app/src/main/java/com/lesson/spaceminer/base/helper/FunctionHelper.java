package com.lesson.spaceminer.base.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.lesson.spaceminer.utils.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * All operation related function declare here
 * Created by spaceminer on 25/10/2022.
 */

public class FunctionHelper {
    Activity activity;

    public FunctionHelper(Activity activity) {
        this.activity = activity;
    }

    /**
     * Start Activity quick shortcut
     *
     * @param c
     */
    public void startActivityQuick(Class c) {
        Intent next = new Intent(activity, c);
        activity.startActivity(next);
        activity.finish();
    }

    /**
     * Hide Keyboard function using view
     *
     * @param view
     */
    public void hideKeyboard(View view) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * show keyboard function
     *
     * @param view
     */
    public void showKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shortcut to get edit text text
     *
     * @param t
     * @return
     */
    public String getEditText(EditText t) {
        if (t.getText() != null && t.getText().toString().trim().length() > 0 && !t.getText().toString().trim().equalsIgnoreCase(Constants.EMPTY_TEXT)) {
            return t.getText().toString().trim();
        } else
            return null;
    }

    /**
     * Get device id function
     *
     * @return
     */
    public String getDeviceId() {
        String deviceId = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return deviceId;
    }

    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    public boolean isEmailValid(String email) {
        String expression = "[a-zA-Z0-9+._%-+]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                "(" +
                "." +
                "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                ")+";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * show info page and hide recycler view
     *
     * @param viewInfo
     * @param viewContent
     * @param tvInfo
     * @param msg
     */
    public void showInfoPage(View viewInfo, View viewContent, TextView tvInfo, String msg) {
        viewInfo.setVisibility(View.VISIBLE);
        tvInfo.setText(msg);
        viewContent.setVisibility(View.INVISIBLE);
    }

    /**
     * Hide error layout
     *
     * @param viewInfo
     * @param viewContent
     */
    public void hideInfoPage(View viewInfo, View viewContent) {
        viewInfo.setVisibility(View.INVISIBLE);
        viewContent.setVisibility(View.VISIBLE);
    }

}
