package com.example.commerce.view;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commerce.R;
import com.example.commerce.adapter.ViewPagerAdapter;
import com.example.commerce.databinding.FragmentItemOfProductBinding;
import com.example.commerce.model.ImagesItem;
import com.example.commerce.model.Response;
import com.example.commerce.network.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemOfProductFragment extends Fragment {

    public static final String ARG_PRODUCT_ID = "product_id";
    ProductRepository mProductRepository;
    MutableLiveData<Response> mItemLiveData = new MutableLiveData<>();

    FragmentItemOfProductBinding mBinding;
    private int mProductId;
    List<String> src;

    public static ItemOfProductFragment newInstance(int productId) {
        Bundle args = new Bundle();
        args.putInt(ARG_PRODUCT_ID, productId);
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
        mProductId = getArguments().getInt(ARG_PRODUCT_ID);
        mProductRepository = ProductRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_of_product, container, false);

       // ViewPagerAdapter mViewPager = new ViewPagerAdapter(getContext(), updateViewPager());
        /*mBinding.productName.setText(mItemLiveData.getValue().getName());
        mBinding.txtOrginalPrice.setText(mItemLiveData.getValue().getRegularPrice()); // regularPrice or price
        mBinding.txtSalePrice.setText(mItemLiveData.getValue().getSalePrice());
        mBinding.txtProductDescription.setText(mItemLiveData.getValue().getDescription());*/
       // mBinding.itemViewPager.setAdapter(mViewPager);
        mBinding.executePendingBindings();
        return mBinding.getRoot();
    }

    /*public List<String> updateViewPager() {
        mItemLiveData = mProductRepository.getItem(mProductId);
        mItemLiveData.observe(getActivity(), new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                for (ImagesItem imagesItem: response.getImages()) {
                    src.add(imagesItem.getSrc());
                }
            }
        });
        return src;
    }*/
/*
    private class getItemOfProduct extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }*/
}
