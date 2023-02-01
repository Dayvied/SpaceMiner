package com.lesson.spaceminer.fragment.browse;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.lesson.spaceminer.R;
import com.lesson.spaceminer.adapter.SpaceListAdapter;
import com.lesson.spaceminer.base.BaseFragment;
import com.lesson.spaceminer.databinding.FragmentBrowseBinding;
import com.lesson.spaceminer.model.SpaceObj;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Browse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Browse extends BaseFragment {

    private FragmentBrowseBinding binding;
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

    public Browse() {
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
    public static Browse newInstance(String param1, String param2) {
        Browse fragment = new Browse();
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
        binding =  FragmentBrowseBinding.inflate(getLayoutInflater());
        uiHelper.setStatusBarTransparent();
        setData();
        setupListener();
        return binding.getRoot();
    }

    public void setBrowse() {

    }

    private void setData() {
        spaceList.add(new SpaceObj("1", "121", "active"));
        spaceList.add(new SpaceObj("2", "122", "active"));
        spaceList.add(new SpaceObj("3", "123", "active"));
        spaceList.add(new SpaceObj("4", "124", "active"));
        spaceList.add(new SpaceObj("5", "125", "active"));

        if(spaceList.size() > 0) {
            binding.rvSpaces.setVisibility(View.VISIBLE);
            binding.tvNoData.setVisibility(View.GONE);
            //setupRvScrollListener();
            gridLayoutManager = new GridLayoutManager(context, 1);
            binding.rvSpaces.setLayoutManager(gridLayoutManager);
            listAdapter = new SpaceListAdapter(context, spaceList, (pos, orderID) -> {
//                Intent details = new Intent(context, OrderDetails.class);
//                details.putExtra(ORDER_ID, orderID);
//                startActivity(details);
            });
            binding.rvSpaces.setAdapter(listAdapter);
        }else {
            binding.rvSpaces.setVisibility(View.GONE);
            binding.tvNoData.setVisibility(View.VISIBLE);
        }
    }

    private void goToFilterSpace() {
        communicator.hideBottomNav();
        FilterSpace filterSpace = new FilterSpace();
        filterSpace.setListenerFilterSpace(new FilterSpace.OnItemClickListener() {

            @Override
            public void goToFragment(Fragment f) {

            }

            @Override
            public void goBack() {
                listener.goBack();
                communicator.showBottomNav();
            }

        });
        listener.goToFragment(filterSpace);
    }

    /**
     * @author david
     * listener
     */
    OnItemClickListener listener;

    public void setListenerBrowse(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void bindComponents(ViewGroup rootView) {

    }

    @Override
    public void setupListener() {
        binding.rlSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.rl_search) {
            goToFilterSpace();
        }
    }

    public interface OnItemClickListener {

        void goToFragment(Fragment f);

        void goBack();

    }
}