package com.example.commerce.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commerce.R;
import com.example.commerce.databinding.FragmentCategoryPagerBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryPagerFragment extends Fragment {

    public static final String ARG_POSITION = "position";
    private FragmentCategoryPagerBinding mBinding;

    public static CategoryPagerFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        CategoryPagerFragment fragment = new CategoryPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_pager, container, false);
        mBinding.executePendingBindings();
        return mBinding.getRoot();
    }

}
