package com.example.commerce.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.commerce.R;
import com.example.commerce.databinding.FragmentItemOfProductBinding;
import com.example.commerce.model.Response;
import com.example.commerce.network.ProductRepository;
import com.example.commerce.viewmodel.CommerceFragmentViewModel;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemOfProductFragment extends Fragment {

    public static final String ARG_PRODUCT_ID = "product_id";
    ProductRepository mProductRepository;
    CommerceFragmentViewModel mViewModel;

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
        mViewModel = ViewModelProviders.of(this).get(CommerceFragmentViewModel.class);
        mViewModel.getItemProductLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                initView(response);


                HashMap<String, String> urls = new HashMap<>();
                urls.clear();
                for (int i = 0; i < response.getImages().size(); i++) {
                    urls.put(String.valueOf(i), response.getImages().get(i).getSrc());
                }
                int a = urls.size();
                mBinding.itemSliderLayout.removeAllSliders();
                for (String url: urls.keySet()) {
                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    textSliderView.image(urls.get(url)).setScaleType(BaseSliderView.ScaleType.CenterCrop);
                    mBinding.itemSliderLayout.addSlider(textSliderView);
                }
            }
        });
        mViewModel.getItem(mProductId);
    }

    private void initView(Response response) {
        mBinding.productName.setText(response.getName());
        mBinding.txtProductDescription.setText(response.getDescription());
        mBinding.txtOrginalPrice.setText(response.getRegularPrice());
        mBinding.txtSalePrice.setText(response.getSalePrice());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_of_product, container, false);
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
