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
import com.example.commerce.adapter.CategoryAndSearchAdapter;
import com.example.commerce.databinding.FragmentCategoryPagerBinding;
import com.example.commerce.model.Response;
import com.example.commerce.viewmodel.ProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryPagerFragment extends Fragment implements CommerceFragment.searchCallbacks{ //implements ProductRepository.ProductRepositoryCallbackListproductInCategory {

    private String TAG = "CategoryPagerFragment";

    public static final String ARG_CATEGORY_ID = "position";
    private FragmentCategoryPagerBinding mBinding;
    private int mCategoryId;
    private ProductViewModel mViewModel;
    private CategoryAndSearchAdapter mCategoryAndSearchAdapter;
    CommerceFragment commerceFragment = new CommerceFragment();
    /*private ProductRepository mProductRepository;
    private List<Response> mListProductInCategory;*/

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
        mViewModel.getSubCategoryLiveData().observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(List<Response> responses) {
                setUpSubCategoryAdapter(responses);
            }
        });
        mViewModel.getListProductInCategoriy(mCategoryId);

        commerceFragment.setSearchCallbacks(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_pager, container, false);
        mBinding.executePendingBindings();
        mBinding.subcategoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return mBinding.getRoot();
    }
    public void setUpSubCategoryAdapter(List<Response> listLiveData) {
        /*mProductRepository = ProductRepository.getInstance();
        mProductRepository.getListProductInCategoriy(mCategoryId);
        mProductRepository.setCallbackListproductInCategory(this);*/
        if (isAdded()) {
            if (mCategoryAndSearchAdapter == null) {
                mCategoryAndSearchAdapter = new CategoryAndSearchAdapter(getContext(), listLiveData);
                mBinding.subcategoryRecycler.setAdapter(mCategoryAndSearchAdapter);

            }
            mCategoryAndSearchAdapter.setResponseList(listLiveData);
            mCategoryAndSearchAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void setUpAdapter(List<Response> listResponse) {
        setUpSubCategoryAdapter(listResponse);
    }
/*
    @Override
    public void onResponse(List<Response> productInCategory) {
        mListProductInCategory = productInCategory;
        setUpSubCategoryAdapter();
    }*/

}
