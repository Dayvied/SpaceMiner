package com.lesson.spaceminer.fragment.favorite;

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

public class FavoriteParent extends BaseFragment {
    FrameLayout fragment_container;
    Favorite favorite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = getRootView(inflater, container, R.layout.fragment_tab_favorite);
        //initial first fragment
        initFirstFragment();

        return rootView;
    }

    @Override
    public void bindComponents(ViewGroup rootView) {
        //fragment
        fragment_container = rootView.findViewById(R.id.fragment_container_favorite);
    }

    @Override
    public void setupListener() {

    }

    public void reAttachFragment() {
        popAllFragment();
        reCurrentAttachFragment(favorite);
    }

  /**
     * Initial first fragment
     */
    private void initFirstFragment() {
        favorite = new Favorite();
        //set all listener for shop
        favorite.setListenerFavorite(new Favorite.OnItemClickListener() {

            @Override
            public void goToFragment(Fragment f) {
                loadFragment(f, Constants.VIEW_PAGER_FAVORITE, R.id.fragment_container_favorite);
            }

            @Override
            public void goBack() {
                popFragment();
            }

        });
        initFragment(favorite, R.id.fragment_container_favorite);
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
    FavoriteParentListener listener;

    public void setListener(FavoriteParentListener listener) {
        this.listener = listener;
    }

    public interface FavoriteParentListener {


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
