package com.example.commerce.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commerce.R;
import com.example.commerce.databinding.FragmentItemOfProductBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemOfProductFragment extends Fragment {

    public static final String ARG_PRODUCT_ID = "product_id";

    FragmentItemOfProductBinding mBinding;
    private String mProductId;

    public static ItemOfProductFragment newInstance(String productId) {
        Bundle args = new Bundle();
        args.getString(ARG_PRODUCT_ID, productId);
        ItemOfProductFragment fragment = new ItemOfProductFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public ItemOfProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductId = getArguments().getString(ARG_PRODUCT_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_of_product, container, false);
        mBinding.executePendingBindings();
        return mBinding.getRoot();
    }

}
