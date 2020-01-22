package com.example.commerce.view.listProduct;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commerce.R;
import com.example.commerce.adapter.AllProductAdapter;
import com.example.commerce.adapter.EndlessRecyclerViewScrollListener;
import com.example.commerce.databinding.FragmentAllProductBinding;
import com.example.commerce.model.product.Response;
import com.example.commerce.viewmodel.OrderProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllProductSearchFragment extends Fragment {

    public static final String ARG_QUERY = "query";
    private FragmentAllProductBinding mBinding;
    private OrderProductViewModel mViewModel;
    private AllProductAdapter mAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    String query; // chera vaghti private gozashtam mige access nist?????????????????
    int pageNumber =1;

    public static AllProductSearchFragment newInstance(String query) {

        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        AllProductSearchFragment fragment = new AllProductSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AllProductSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String query = getArguments().getString(ARG_QUERY);
        mViewModel = ViewModelProviders.of(this).get(OrderProductViewModel.class);
        mViewModel.searchProduct(query, pageNumber).observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(List<Response> responses) {
                setUpAllProductAdapter(responses);
            }
        });
        //mViewModel.searchProduct(query, pageNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_product, container, false);
        LinearLayoutManager linear = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBinding.recyclerAllProduct.setLayoutManager(linear);
        scrollListener = new EndlessRecyclerViewScrollListener(linear) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                //loadNextDataFromApi(page);

                pageNumber++;
                mViewModel.searchProduct(query, pageNumber);
            }
        };
        // Adds the scroll listener to RecyclerView
        mBinding.recyclerAllProduct.addOnScrollListener(scrollListener);
        return mBinding.getRoot();
    }

    public void setUpAllProductAdapter(List<Response> responseList) {
        if (isAdded()) {
            mAdapter = new AllProductAdapter(getContext(), responseList);
            mBinding.recyclerAllProduct.setAdapter(mAdapter);
        }else {
            mAdapter.setResponseList(responseList);
            mAdapter.notifyDataSetChanged();
        }
    }

}
