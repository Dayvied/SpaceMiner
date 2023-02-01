package com.lesson.spaceminer.base;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BackPressImpl implements OnBackPressListener {

    private final Fragment parentFragment;

    public BackPressImpl(Fragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @Override
    public boolean onFragmentBackPressed() {
        //Logs.logError("fragment", "childcount parent null");
        if (parentFragment == null)
            return false;

        int childCount = parentFragment.getChildFragmentManager().getBackStackEntryCount();
        //Logs.logError("fragment", "childcount "+childCount);
        if (childCount == 0) {
            // it has no child Fragment
            // can not handle the onBackPressed task by itself
            return false;

        } else {
            // get the child Fragment
            FragmentManager childFragmentManager = parentFragment.getChildFragmentManager();
            OnBackPressListener childFragment = (OnBackPressListener) childFragmentManager.getFragments().get(0);

            // propagate onBackPressed method call to the child Fragment
            if (!childFragment.onFragmentBackPressed()) {
                // child Fragment was unable to handle the task
                // It could happen when the child Fragment is last last leaf of a chain
                // removing the child Fragment from stack
                childFragmentManager.popBackStackImmediate();

            }

            // either this Fragment or its child handled the task
            // either way we are successful and done here
            return true;
        }
    }
}
