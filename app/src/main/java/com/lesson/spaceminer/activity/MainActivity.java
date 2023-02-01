package com.lesson.spaceminer.activity;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lesson.spaceminer.R;
import com.lesson.spaceminer.base.BaseActivity;
import com.lesson.spaceminer.base.BaseFragment;
import com.lesson.spaceminer.customUI.NoSwipeViewPager;
import com.lesson.spaceminer.databinding.ActivityHomeBinding;
import com.lesson.spaceminer.fragment.browse.Browse;
import com.lesson.spaceminer.fragment.browse.BrowseParent;
import com.lesson.spaceminer.fragment.favorite.Favorite;
import com.lesson.spaceminer.fragment.favorite.FavoriteParent;
import com.lesson.spaceminer.pager.CommonPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BaseFragment.Communicator {

    private ActivityHomeBinding binding;
    public static int pagePosition = 0;
    public static List<RelativeLayout> btnList = new ArrayList<>();
    public static List<TextView> tv = new ArrayList<>();
    public static List<ImageView> ivTabList = new ArrayList<>();

    //fragment related
    BrowseParent browseParent;
    FavoriteParent favoriteParent;

    NoSwipeViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initViewPager();
        setupListener();

        //search favourite profile
        //small-medium-big
    }

    @Override
    public void bindComponents() {
        btnList.clear();
        btnList.add(binding.bottomNav.btnBrowse);
        btnList.add(binding.bottomNav.btnFavorite);
        btnList.add(binding.bottomNav.btnBooking);
        btnList.add(binding.bottomNav.btnProfile);

        //create list of text view
        tv.clear();
        tv.add(binding.bottomNav.tvBrowse);
        tv.add(binding.bottomNav.tvFavorite);
        tv.add(binding.bottomNav.tvBooking);
        tv.add(binding.bottomNav.tvProfile);

        //create list of image view
        ivTabList.clear();
        ivTabList.add(binding.bottomNav.ivBrowse);
        ivTabList.add(binding.bottomNav.ivFavorite);
        ivTabList.add(binding.bottomNav.ivBooking);
        ivTabList.add(binding.bottomNav.ivProfile);

    }

    @Override
    public void setupListener() {
        for (int i = 0; i < btnList.size(); i++) {
            btnList.get(i).setOnClickListener(this);
        }
    }

    /**
     * Init view pager
     */
    private void initViewPager() {
        CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager());
        //initial home tab
        browseParent = new BrowseParent();
        browseParent.setListener(new BrowseParent.BrowseParentListener() {

        });

        //initial favorite tab
        favoriteParent = new FavoriteParent();
        favoriteParent.setListener(new FavoriteParent.FavoriteParentListener() {

        });

        adapter.addFragment(browseParent);
        adapter.addFragment(favoriteParent);
        binding.viewPagerMain.setAdapter(adapter);
        //set don't destroy
        binding.viewPagerMain.setOffscreenPageLimit(5);
        binding.viewPagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) { }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });
        changePage(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_browse:
                changePage(0);
                break;
            case R.id.btn_favorite:
                changePage(1);
                break;
            case R.id.btn_booking:
                changePage(2);
                break;
            case R.id.btn_profile:
                changePage(3);
                break;
        }
    }

    private void changePage(int position) {
        if (pagePosition == 0 && position == 0) {
            //shop.backProgramScreen();
        }
        pagePosition = position;
        binding.viewPagerMain.setCurrentItem(position, false);
        for (int i = 0; i < btnList.size(); i++) {
            if (position == i) {
                btnList.get(i).setEnabled(false);
                tv.get(i).setTextColor(getColor(R.color.primary));
            } else {
                btnList.get(i).setEnabled(true);
                tv.get(i).setTextColor(getColor(R.color.black));
            }
        }
        Log.d("checkt",btnList.size()+"");
        btnList.get(0).setEnabled(true);

        resetTabImg(position);
    }

    private void resetTabImg(int position) {
        for (int i = 0; i < 4; i++) {
            ImageView imageView = ivTabList.get(i);
            if (i == 0) {
                imageView.setColorFilter(getColor(R.color.grey));
            } else if (i == 1) {
                imageView.setColorFilter(getColor(R.color.grey));
            } else if (i == 2) {
                imageView.setColorFilter(getColor(R.color.grey));
            } else if (i == 3) {
                imageView.setColorFilter(getColor(R.color.grey));
            } else {
                imageView.setColorFilter(getColor(R.color.grey));
            }
        }

        if (position == 0) {
            ivTabList.get(position).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.primary), PorterDuff.Mode.SRC_IN);
        } else if (position == 1) {
            ivTabList.get(position).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.primary), PorterDuff.Mode.SRC_IN);
        } else if (position == 2) {
            ivTabList.get(position).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.primary), PorterDuff.Mode.SRC_IN);
        } else if (position == 3) {
            ivTabList.get(position).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.primary), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    public void hideBottomNav() {
        binding.bottomNav.tabRoot.setVisibility(View.GONE);
    }

    @Override
    public void showBottomNav() {
        binding.bottomNav.tabRoot.setVisibility(View.VISIBLE);
    }
}