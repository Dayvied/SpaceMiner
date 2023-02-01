package com.lesson.spaceminer.base.helper;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by spaceminer on 25/10/2022.
 */

public class FragmentHelper {

    Activity activity;

    public FragmentHelper(Activity activity) {
        this.activity = activity;
    }

    /**
     * change fragment of Tab
     *
     * @param fragment
     * @param parentLayout
     * @param frameLayout
     */
    public void loadFragment(FragmentManager fragmentManager, Fragment fragment, String parentLayout, int frameLayout) {
        Fragment currentFragment = fragmentManager.findFragmentById(frameLayout);

        // create a FragmentManager
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // replace the FrameLayout with new Fragment
        transaction.add(frameLayout, fragment);
        transaction.hide(currentFragment);
        transaction.addToBackStack(parentLayout);
        transaction.commit(); // save the changes
    }

    public void reattachFragments(FragmentManager fragmentManager, Fragment fragment) {
        //Fragment currentFragment = fragmentManager.findFragmentByTag("YourFragmentTag");
        //Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container_shop);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.detach(fragment);
        fragmentTransaction.attach(fragment);
        fragmentTransaction.commit();
    }

    /**
     * replace current fragment
     *
     * @param fragmentManager
     * @param fragment
     * @param parentLayout
     * @param frameLayout
     */
    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, String parentLayout, int frameLayout) {
        // create a FragmentManager
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // replace the FrameLayout with new Fragment
        transaction.replace(frameLayout, fragment);
        transaction.commit(); // save the changes
    }

    /**
     * Pop fragment from onBack stack function
     */
    public boolean popFragment(FragmentManager fragmentManager) {
        //System.out.println("check popfragm "+fragmentManager.getBackStackEntryCount());
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
            return true;
        }
        return false;
    }

    /**
     * pop all fragment
     *
     * @param fragmentManager
     */
    public void popAllFragment(FragmentManager fragmentManager) {
        for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }

    /**
     * Initial fragment for each tab
     *
     * @param fragmentManager
     * @param fragment
     * @param frameLayout
     */
    public void initFragment(FragmentManager fragmentManager, Fragment fragment, int frameLayout) {
        // create a FragmentManager
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        // add the FrameLayout with new Fragment
        transaction.add(frameLayout, fragment, "SHOP1");
        transaction.commit(); // save the changes
    }

    /**
     * show dialog fragment
     *
     * @param fragmentManager
     * @param dialogFragment
     * @param tag
     */
    public void showDialogFragment(FragmentManager fragmentManager, Fragment dialogFragment, String tag) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(dialogFragment, tag);
        ft.commit();
    }

    /**
     * change fragment of Tab
     *
     * @param fragmentManager
     * @param frameLayout
     */
    public String getCurrentFragmentName(FragmentManager fragmentManager, int frameLayout) {
        Fragment currentFragment = fragmentManager.findFragmentById(frameLayout);
        if (currentFragment == null) {
            return "";
        }
        return currentFragment.getClass().getSimpleName();
    }
}
