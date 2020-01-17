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
import com.example.commerce.adapter.AllProductAdapter;
import com.example.commerce.databinding.FragmentProductOfSubcategoryBinding;
import com.example.commerce.model.Response;
import com.example.commerce.viewmodel.ProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductOfSubcategoryFragment extends NetworkFragment {

    public static final String ARG_CATEGORY_ID = "categoryId";
    private FragmentProductOfSubcategoryBinding mBinding;
    private ProductViewModel mViewModel;
    private AllProductAdapter mAdapter;
    private int mCategoryId;

    public static ProductOfSubcategoryFragment newInstance(int categoryid) {
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY_ID, categoryid);
        ProductOfSubcategoryFragment fragment = new ProductOfSubcategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProductOfSubcategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = getArguments().getInt(ARG_CATEGORY_ID);
        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mViewModel.getListProductInCategory(mCategoryId).observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(List<Response> responseList) {
                setUpListSubCategory(responseList);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_of_subcategory, container, false);
        mBinding.recyclerProductOfSubcategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return mBinding.getRoot();
    }

    public void setUpListSubCategory( List<Response> responseList) {
        if (isAdded()){
            if (mAdapter == null){
                mAdapter = new AllProductAdapter(getContext(),responseList);
                mBinding.recyclerProductOfSubcategory.setAdapter(mAdapter);
            }else {
                mAdapter.setResponseList(responseList);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

}
