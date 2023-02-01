package com.lesson.spaceminer.base;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.lesson.spaceminer.base.helper.FragmentHelper;
import com.lesson.spaceminer.base.helper.FunctionHelper;
import com.lesson.spaceminer.base.helper.MessageHelper;
import com.lesson.spaceminer.base.helper.UIHelper;
import com.lesson.spaceminer.utils.PrefManager;

import java.util.Locale;


/**
 * Created by spaceminer on 25/10/2022.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public final String TAG = this.getClass().getSimpleName();
    //UI helper class
    UIHelper uiHelper = new UIHelper(this);

    //Function helper class
    FunctionHelper functionHelper = new FunctionHelper(this);

    //Message helper class
    MessageHelper messageHelper = new MessageHelper(this);

    //fragment helper
    FragmentHelper fragmentHelper = new FragmentHelper(this);

    public RelativeLayout viewLoader;
    private Locale mCurrentLocale;
    private PrefManager p;
    private int layoutID;

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        layoutID = layoutResID;
        //bind components to XML
        bindComponents();
        p = new PrefManager(BaseActivity.this);
        setupListener();

    }

    public void setLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    public void reAttachFragment() {
        //fragmentHelper.reattachFragments(getSupportFragmentManager());
    }

    /*@Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/

   /* @Override
    protected void onResume() {
        App.currentActivity = this;
        App.appOpen = true;
        super.onResume();
    }
*/

    /**
     * Set full screen activity
     */
    public void setFullScreen() {
        uiHelper.setFullScreen();
    }

    /**
     * Set status bar color
     *
     * @param color
     */
    public void setStatusBar(int color) {
        uiHelper.setStatusBar(color);
    }

    /**
     * Set window status bar fully transparent
     */
    public void setStatusBarTransparent() {
        uiHelper.setStatusBarTransparent();
    }

    /**
     * set status bar as padding top
     *
     * @param v
     */
    public void setPaddingTopBelowStatusBar(View v) {
        uiHelper.setPaddingTopBelowStatusBar(v);
    }

    /**
     * Get the actionBar height
     *
     * @return
     */
    public int getActionBarHeight() {
        return uiHelper.getActionBarHeight();
    }

    //function helper function start

    /**
     * show info page and hide recycler view
     *
     * @param viewInfo
     * @param viewContent
     * @param tvInfo
     * @param msg
     */
    public void showInfoPage(View viewInfo, View viewContent, TextView tvInfo, String msg) {
        functionHelper.showInfoPage(viewInfo, viewContent, tvInfo, msg);
    }

    /**
     * Hide error layout
     *
     * @param viewInfo
     * @param viewContent
     */
    public void hideInfoPage(View viewInfo, View viewContent) {
        functionHelper.hideInfoPage(viewInfo, viewContent);
    }

    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return
     */
    public boolean isEmailValid(String email) {
        return functionHelper.isEmailValid(email);
    }

    /**
     * Hide Keyboard function using view
     *
     * @param view
     */
    public void hideKeyboard(View view) {
        functionHelper.hideKeyboard(view);
    }

    /**
     * show keyboard function
     *
     * @param view
     */
    public void showKeyboard(View view) {
        functionHelper.showKeyboard(view);
    }

    /**
     * Start Activity quicky shortcut
     *
     * @param c
     */
    public void startActivityQuick(Class c) {
        functionHelper.startActivityQuick(c);
    }

    /**
     * Get device id function
     *
     * @return
     */
    public String getDeviceId() {
        return functionHelper.getDeviceId();
    }

    private static Boolean checkConnectivity(Context c) {

        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for internet connection
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;

    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    //message helper function start

    /**
     * Toast message short duration
     *
     * @param msg
     */
    public void toastShort(String msg) {
        messageHelper.toastShort(msg);
    }

    /**
     * Toast message short duration with y offset
     *
     * @param msg
     */
    public void toastShortWithGravity(String msg, int yOffSet) {
        messageHelper.toastShortWithGravity(msg, yOffSet);
    }

    /**
     * Snack message short duration
     *
     * @param msg
     */
    public void snackShort(String msg, int layout) {
        messageHelper.snackShort(msg, layout);
    }

    //fragment helper function start

    /**
     * change fragment of Tab
     *
     * @param fragment
     * @param parentLayout
     * @param frameLayout
     */
    public void loadFragment(Fragment fragment, String parentLayout, int frameLayout) {
        fragmentHelper.loadFragment(getSupportFragmentManager(), fragment, parentLayout, frameLayout);
    }

    /**
     * Pop fragment from back stack function
     */
    public void popFragment() {
        fragmentHelper.popFragment(getSupportFragmentManager());
    }

    /**
     * Pop fragment from back stack function
     */
    public void popAllFragment() {
        fragmentHelper.popAllFragment(getSupportFragmentManager());
    }

    /**
     * Initial fragment for each tab
     *
     * @param fragment
     * @param frameLayout
     */
    public void initFragment(Fragment fragment, int frameLayout) {
        fragmentHelper.initFragment(getSupportFragmentManager(), fragment, frameLayout);
    }

    /**
     * Get current fragment name
     *
     * @param frameLayout
     * @return
     */
    public String getCurrentFragmentName(int frameLayout) {
        return fragmentHelper.getCurrentFragmentName(getSupportFragmentManager(), frameLayout);
    }

    /**
     * show loading dialog
     *
     * @param title
     * @param msg
     */
    public void showLoadingDialog(RelativeLayout rlLoader, final String title, final String msg) {
        try {
            viewLoader = rlLoader;
            rlLoader.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            //Logs.logError(TAG, e.getMessage());
        }
    }

    /**
     * Dismiss loading dialog
     */
    public void dismissLoadingDialog() {
        try {
            if(viewLoader != null)
                viewLoader.setVisibility(View.GONE);
        } catch (Exception e) {
            //Logs.logError(TAG, e.getMessage());
        }
    }

    /**
     * Bind components to activity
     * need override in every activity
     */
    public abstract void bindComponents();

    /**
     * Setup listener for each components
     * need override in every activity
     */
    public abstract void setupListener();
}
