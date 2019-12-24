package com.example.commerce.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.commerce.R;
import com.example.commerce.databinding.CommerceFragmentBinding;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommerceFragment extends Fragment {

    CommerceFragmentBinding mBinding;

    public static CommerceFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CommerceFragment fragment = new CommerceFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public CommerceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.commerce_fragment, container, false);
        mBinding.executePendingBindings();

        setUpSlider();
        return mBinding.getRoot();
    }

    private void setUpSlider(){
        HashMap<String, Integer> images = new HashMap<>();
        images.put("image1",R.drawable.image1);
        images.put("image2", R.drawable.image2);
        images.put("image3", R.drawable.image3);
        for (String imageName: images.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView.description(imageName).image(images.get(imageName)).setScaleType(BaseSliderView.ScaleType.CenterCrop);
            mBinding.sliderLayout.addSlider(textSliderView);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);

    }
}
