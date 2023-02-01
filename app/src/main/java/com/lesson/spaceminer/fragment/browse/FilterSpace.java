package com.lesson.spaceminer.fragment.browse;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.lesson.spaceminer.R;
import com.lesson.spaceminer.adapter.SpaceListAdapter;
import com.lesson.spaceminer.base.BaseFragment;
import com.lesson.spaceminer.databinding.FragmentBrowseBinding;
import com.lesson.spaceminer.databinding.FragmentFilterSpaceBinding;
import com.lesson.spaceminer.model.SpaceObj;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterSpace#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterSpace extends BaseFragment {

    private FragmentFilterSpaceBinding binding;
    private List<SpaceObj> spaceList = new ArrayList<>();
    private Context context;
    private SpaceListAdapter listAdapter;
    private GridLayoutManager gridLayoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FilterSpace() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static FilterSpace newInstance(String param1, String param2) {
        FilterSpace fragment = new FilterSpace();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentFilterSpaceBinding.inflate(getLayoutInflater());
        uiHelper.setStatusBarTransparent();
        setData();
        setupListener();
        return binding.getRoot();
    }

    public void setBrowse() {

    }

    private void setData() {

    }


    /**
     * @author david
     * listener
     */
    OnItemClickListener listener;

    public void setListenerFilterSpace(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void bindComponents(ViewGroup rootView) {

    }

    @Override
    public void setupListener() {
        binding.ivClose.setOnClickListener(this);
        binding.rlWhere.setOnClickListener(this);
        binding.rlWhen.setOnClickListener(this);
        binding.rlSpaceSize.setOnClickListener(this);
        binding.tvSmall.setOnClickListener(this);
        binding.tvMedium.setOnClickListener(this);
        binding.tvLarge.setOnClickListener(this);
        binding.tvExtraLarge.setOnClickListener(this);

        binding.btnClearAll.setOnClickListener(this);
        binding.btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.rl_where) {
            if(binding.rlWhereHidden.getVisibility() == View.GONE) {
                binding.rlWhereHidden.setVisibility(View.VISIBLE);
            }else {
                binding.rlWhereHidden.setVisibility(View.GONE);
            }
        }else if(view.getId() == R.id.rl_when) {
            if(binding.rlWhenHidden.getVisibility() == View.GONE) {
                binding.rlWhenHidden.setVisibility(View.VISIBLE);
            }else {
                binding.rlWhenHidden.setVisibility(View.GONE);
            }
        }else if(view.getId() == R.id.rl_spaceSize) {
            if(binding.rlSizeHidden.getVisibility() == View.GONE) {
                binding.rlSizeHidden.setVisibility(View.VISIBLE);
            }else {
                binding.rlSizeHidden.setVisibility(View.GONE);
            }
        }else if(view.getId() == R.id.iv_close) {
            listener.goBack();
        }else if(view.getId() == R.id.tv_small) {
            setSize(binding.tvSmall);
        }else if(view.getId() == R.id.tv_medium) {
            setSize(binding.tvMedium);
        }else if(view.getId() == R.id.tv_large) {
            setSize(binding.tvLarge);
        }else if(view.getId() == R.id.tv_extraLarge) {
            setSize(binding.tvExtraLarge);
        }else if(view.getId() == R.id.btn_clearAll) {

        }else if(view.getId() == R.id.btn_search) {
            listener.goBack();
        }
    }

    private void setSize(TextView tv) {
        binding.tvSmall.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_corner_white_ii, null));
        binding.tvMedium.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_corner_white_ii, null));
        binding.tvLarge.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_corner_white_ii, null));
        binding.tvExtraLarge.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_corner_white_ii, null));
        tv.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_corner_selected_ii, null));
        int pad = (int)getResources().getDimension(R.dimen.padding_i);

        binding.tvSmall.setPadding(pad, pad, pad, pad);
        binding.tvMedium.setPadding(pad, pad, pad, pad);
        binding.tvLarge.setPadding(pad, pad, pad, pad);
        binding.tvExtraLarge.setPadding(pad, pad, pad, pad);
    }

    public interface OnItemClickListener {

        void goToFragment(Fragment f);

        void goBack();

    }
}