package com.example.commerce.view.cart;


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
import com.example.commerce.adapter.CartAdapter;
import com.example.commerce.databinding.FragmentCartBinding;
import com.example.commerce.model.product.Response;
import com.example.commerce.view.networkCheck.NetworkFragment;
import com.example.commerce.viewmodel.ProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends NetworkFragment {

    public static final String TAG = "CartFragment";
    public static final String ARG_PRODUCT_ID = "productId";

    private FragmentCartBinding mBinding;
    private ProductViewModel mViewModel;
    private CartAdapter mCartAdapter;
    private int mCount;

    private int mProductid;
    //private List<Integer> mListProductId = new ArrayList<>();

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
        //mListProductId.add(mProductid);
        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mViewModel.getProductOfCart().observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(List<Response> responseList) {
                setUpAdapter(responseList);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
       mBinding.recyclerCart.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
       return mBinding.getRoot();
    }

    public void setUpAdapter(List<Response> responseList) {
        if (isAdded()) {
           if (mCartAdapter == null) {
               mCartAdapter = new CartAdapter(getContext(), responseList);
               mBinding.recyclerCart.setAdapter(mCartAdapter);
           }else {
               mCartAdapter.setResponseList(responseList);
               mCartAdapter.notifyDataSetChanged();
           }
        }
    }

}
