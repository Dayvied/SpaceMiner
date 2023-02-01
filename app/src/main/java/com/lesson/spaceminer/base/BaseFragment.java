package com.lesson.spaceminer.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.lesson.spaceminer.base.helper.FragmentHelper;
import com.lesson.spaceminer.base.helper.FunctionHelper;
import com.lesson.spaceminer.base.helper.MessageHelper;
import com.lesson.spaceminer.base.helper.UIHelper;

public abstract class BaseFragment extends Fragment implements View.OnClickListener, OnBackPressListener {

    public final String TAG = this.getClass().getSimpleName();
    //UI helper
    public UIHelper uiHelper;
    //function helper
    public FunctionHelper functionHelper;
    //message helper
    public MessageHelper messageHelper;
    //fragment helper
    FragmentHelper fragmentHelper;

    public RelativeLayout viewLoader;

    /**
     * Listener for main activity
     */
    public static Communicator communicator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //initial all helper class here
            //ui helper
            uiHelper = new UIHelper((Activity) context);
            //function helper
            functionHelper = new FunctionHelper((Activity) context);
            //message helper
            messageHelper = new MessageHelper((Activity) context);
            //fragment helper
            fragmentHelper = new FragmentHelper((Activity) context);

            communicator = (Communicator) getActivity();
        } catch (ClassCastException e) {
            //Logs.logError(TAG, "Error in retrieving data. Please try again");
        }
    }

    //fragment helper function start

    /**
     * change fragment of Tab
     *
     * @param fragment fragment
     * @param parentLayout parentLayout
     * @param frameLayout frameLayout
     */
    public void loadFragment(Fragment fragment, String parentLayout, int frameLayout) {
        fragmentHelper.loadFragment(getChildFragmentManager(), fragment, parentLayout, frameLayout);
    }

    /**
     * Pop fragment from back stack function
     */
    public void popFragment() {
        fragmentHelper.popFragment(getChildFragmentManager());
    }

    /**
     * pop all fragment
     */
    public void popAllFragment() {
            fragmentHelper.popAllFragment(getChildFragmentManager());
    }

    /**
     * Initial fragment for each tab
     *
     * @param fragment fragment
     * @param frameLayout frameLayout
     */
    public void initFragment(Fragment fragment, int frameLayout) {
        fragmentHelper.initFragment(getChildFragmentManager(), fragment, frameLayout);
    }

    public void reCurrentAttachFragment(Fragment fragment) {
        fragmentHelper.reattachFragments(getChildFragmentManager(), fragment);
    }

    /**
     * Get current fragment name
     *
     * @param frameLayout frameLayout
     * @return return
     */
    public String getCurrentFragmentName(int frameLayout) {
        return fragmentHelper.getCurrentFragmentName(getChildFragmentManager(), frameLayout);
    }

    //other
    //check empty editText
    public boolean isEditTextFilled(EditText et) {
        return functionHelper.getEditText(et) != null;
    }

    public boolean isTIEditTextFilled(TextInputEditText et) {
        return functionHelper.getEditText(et) != null;
    }

    public boolean isACTextFilled(AutoCompleteTextView et) {
        return functionHelper.getEditText(et) != null;
    }

    /**
     * Inflater layout function
     *
     * @param inflater inflater
     * @param container container
     * @param layout layout
     * @return return
     */
    public ViewGroup getRootView(LayoutInflater inflater, ViewGroup container, int layout) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(layout, container, false);

        //bind components to xml
        bindComponents(rootView);

        //setup listener for each components
        setupListener();

        //set padding below status bar
        return rootView;
    }

    public boolean getIsVisible()
    {
        if (getParentFragment() != null && getParentFragment() instanceof BaseFragment)
        {
            return isVisible() && ((BaseFragment) getParentFragment()).getIsVisible();
        }
        else
        {
            return isVisible();
        }
    }

    /**
     * show loading dialog
     *
     * @param title title
     * @param msg msg
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

    public boolean onBackPressed() {
        return onFragmentBackPressed();
    }

    /**
     * Used by each fragment back pressed function
     *
     * @return return
     */
    @Override
    public boolean onFragmentBackPressed() {
        //Logs.logError("fragment", "childcount start");
        return new BackPressImpl(this).onFragmentBackPressed();
    }

    public interface Communicator {

        void hideBottomNav();

        void showBottomNav();

    }

    /**
     * Bind components to activity
     * need override in every fragment
     *
     * @param rootView rootView
     */
    public abstract void bindComponents(ViewGroup rootView);

    /**
     * Setup listener for each components
     * need override in every activity
     */
    public abstract void setupListener();

}
