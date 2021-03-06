package com.example.commerce.view.category;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.commerce.R;
import com.example.commerce.databinding.FragmentItemOfProductBinding;
import com.example.commerce.model.product.CartProduct;
import com.example.commerce.model.product.Response;
import com.example.commerce.view.cart.CartFragment;
import com.example.commerce.view.networkCheck.NetworkFragment;
import com.example.commerce.viewmodel.ProductViewModel;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemOfProductFragment extends NetworkFragment {

    public static final String ARG_PRODUCT_ID = "product_id";

    ProductViewModel mViewModel;
    FragmentItemOfProductBinding mBinding;
    Response mResponse;
    private int mProductId;

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
        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        mViewModel.getItemProductLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                initView(response);
                mResponse = response;

                mBinding.itemSliderLayout.removeAllSliders();
                for (int i = 0; i < response.getImages().size(); i++) {
                    String url = response.getImages().get(i).getSrc();
                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    textSliderView.image(url).setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
                    mBinding.itemSliderLayout.addSlider(textSliderView);
                }
            }
        });
        mViewModel.getItem(mProductId);
    }

    private void initView(Response response) {
        mBinding.productName.setText(response.getName());
        mBinding.txtProductDescription.setText(convertHtmlToText(response.getDescription()));
        mBinding.txtOrginalPrice.setText(response.getRegularPrice() + " تومان");
        String salePrice = response.getSalePrice();
        if (salePrice != null && !salePrice.isEmpty()) {
            mBinding.txtSalePrice.setText(salePrice + " تومان");
            mBinding.txtSalePrice.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
            mBinding.txtOrginalPrice.setTextColor(Color.RED);
            mBinding.txtOrginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            mBinding.txtOrginalPrice.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
            mBinding.txtSalePrice.setVisibility(View.GONE);
        }
    }

    private String convertHtmlToText(String html) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_of_product, container, false);
        mBinding.executePendingBindings();

        mBinding.btnAddtocart.setOnClickListener(view -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();

            CartProduct cartProduct = realm.createObject(CartProduct.class, mProductId);
//            cartProduct.setId(mProductId);
            cartProduct.setCount(1);
            realm.commitTransaction();
            getActivity().getSupportFragmentManager()
                            .beginTransaction().replace(R.id.container_fragment, CartFragment.newInstance(mProductId))
                            .addToBackStack(CartFragment.TAG)
                            .commit();
        });

        return mBinding.getRoot();
    }
}
