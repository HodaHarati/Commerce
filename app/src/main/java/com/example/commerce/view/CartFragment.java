package com.example.commerce.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commerce.R;
import com.example.commerce.adapter.ProductAdapter;
import com.example.commerce.databinding.FragmentCartBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends NetworkFragment {

    public static final String TAG = "CartFragment";
    public static final String ARG_PRODUCT_ID = "productId";

    private FragmentCartBinding mBinding;

    private int mProductid;
    private List<Integer> mListProductId = new ArrayList<>();

    public static CartFragment newInstance(int productId) {

        Bundle args = new Bundle();
        args.putInt(ARG_PRODUCT_ID, productId);
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductid = getArguments().getInt(ARG_PRODUCT_ID);
        mListProductId.add(mProductid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
       mBinding.executePendingBindings();
       mBinding.recyclerCart.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
       return mBinding.getRoot();
    }

    public void setUpAdapter() {

    }

}
