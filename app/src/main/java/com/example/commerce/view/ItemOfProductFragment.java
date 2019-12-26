package com.example.commerce.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commerce.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemOfProductFragment extends Fragment {

    public static final String ARG_PRODUCT_ID = "product_id";

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_of_product, container, false);
    }

}
