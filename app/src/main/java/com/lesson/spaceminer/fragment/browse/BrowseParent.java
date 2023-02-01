package com.lesson.spaceminer.fragment.browse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lesson.spaceminer.R;
import com.lesson.spaceminer.base.BaseFragment;
import com.lesson.spaceminer.utils.Constants;


/**
 * Created by david on 27/10/2022.
 */

public class BrowseParent extends BaseFragment {
    FrameLayout fragment_container;
    Browse browse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = getRootView(inflater, container, R.layout.fragment_tab_browse);
        //initial first fragment
        initFirstFragment();

        return rootView;
    }

    @Override
    public void bindComponents(ViewGroup rootView) {
        //fragment
        fragment_container = rootView.findViewById(R.id.fragment_container_browse);
    }

    @Override
    public void setupListener() {

    }

    public void reAttachFragment() {
        popAllFragment();
        reCurrentAttachFragment(browse);
    }

  /**
     * Initial first fragment
     */
    private void initFirstFragment() {
        browse = new Browse();
        //set all listener for shop
        browse.setListenerBrowse(new Browse.OnItemClickListener() {

            @Override
            public void goToFragment(Fragment f) {
                loadFragment(f, Constants.VIEW_PAGER_BROWSE, R.id.fragment_container_browse);
            }

            @Override
            public void goBack() {
                popFragment();
            }

        });
        initFragment(browse, R.id.fragment_container_browse);
    }

    @Override
    public void onClick(View v) {

    }

    /*public void backShopScreen() {
        popAllFragment();
    }*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == REQUEST_SETTINGS_GPS){
            p.onActivityResult(requestCode, resultCode, data);
        }*/
    }

    /**
     * Listener related
     */
    BrowseParentListener listener;

    public void setListener(BrowseParentListener listener) {
        this.listener = listener;
    }

    public interface BrowseParentListener {


    }


    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }

    public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    private FragmentRefreshListener fragmentRefreshListener;


    public interface FragmentRefreshListener{
        void onRefresh();
    }
}
