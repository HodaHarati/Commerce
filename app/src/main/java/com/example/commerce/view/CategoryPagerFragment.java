package com.example.commerce.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commerce.R;
import com.example.commerce.adapter.SubCategoryAdapter;
import com.example.commerce.databinding.FragmentCategoryPagerBinding;
import com.example.commerce.model.CategoriesItem;
import com.example.commerce.viewmodel.ProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryPagerFragment extends NetworkFragment{

    private String TAG = "CategoryPagerFragment";

    public static final String ARG_CATEGORY_ID = "position";
    private FragmentCategoryPagerBinding mBinding;
    private int mCategoryId;
    private ProductViewModel mViewModel;
    private SubCategoryAdapter mAdapter;

    public static CategoryPagerFragment newInstance(int categoryId) {
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY_ID, categoryId);
        CategoryPagerFragment fragment = new CategoryPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = getArguments().getInt(ARG_CATEGORY_ID);
        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mViewModel.getSubCategories(mCategoryId).observe(this, new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoriesItems) {
                setUpSubCategoryAdapter(categoriesItems);
            }
        });
//        mViewModel.getSubCategories(mCategoryId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_pager, container, false);
        mBinding.subcategoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return mBinding.getRoot();
    }

    public void setUpSubCategoryAdapter(List<CategoriesItem> listLiveData) {
        if (isAdded()) {
            if (mAdapter == null) {
                mAdapter = new SubCategoryAdapter(getContext(), listLiveData);
                mBinding.subcategoryRecycler.setAdapter(mAdapter);
            } else {
                mAdapter.setCategoriesItemList(listLiveData);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
