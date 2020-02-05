package com.example.commerce.view.listProduct;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.commerce.R;
import com.example.commerce.adapter.ListAdapter;
import com.example.commerce.adapter.CategoryAdapter;
import com.example.commerce.databinding.CommerceFragmentBinding;
import com.example.commerce.databinding.FragmentCategoryPagerBinding;
import com.example.commerce.model.product.CategoriesItem;
import com.example.commerce.model.product.Response;
import com.example.commerce.view.networkCheck.NetworkFragment;
import com.example.commerce.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommerceFragment extends NetworkFragment {

    private String TAG = "CommerceFragment";

    private CommerceFragmentBinding mBinding;
    private FragmentCategoryPagerBinding mCategoryPagerBinding;
    private ProductViewModel mViewModel;

    private ListAdapter mAdapterNewwst;
    private ListAdapter mAdapterMostViseted;
    private ListAdapter mAdapterBest;
    private CategoryAdapter mAdapterCategory;
    private List<Response> mResponseList = new ArrayList<>();

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

        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        /*mViewModel.deleteRecord(getContext());
        Log.d(TAG, "onCreate: DELETE");*/
        mViewModel.getAllCategoriesLiveData().observe(this, new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoriesItems) {
                setUpAdapterCategory(categoriesItems);
                /*for (CategoriesItem category: categoriesItems) {
                    mCategoriesName.add(category.getName());
                }*/
                Log.d(TAG, "onChanged: " + categoriesItems);
            }
        });
        //mViewModel.getAllCategories();  // this method go to splash activity

        mViewModel.getNewestProductLiveData().observe(this, responses -> {
            setUpAdapterNewest(responses);
            mResponseList.addAll(responses);
            // Log.d(TAG, "onChanged: " + responses);
        });

        mViewModel.getMostVisitedLiveData().observe(this, responses -> setUpAdapterMostVisited(responses));
       // mViewModel.getMostVisitedProduct();

        mViewModel.getBestLiveData().observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(List<Response> responses) {
                setUpAdapterBest(responses);
            }
        });

        mViewModel.getItemProductLiveData().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {

                mBinding.sliderLayout.removeAllSliders();
                for (int i = 0; i < response.getImages().size(); i++) {
                    String url = response.getImages().get(i).getSrc();
                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    textSliderView.image(url).setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
                    mBinding.sliderLayout.addSlider(textSliderView);
                }
            }
        });
        mViewModel.getItem(608);


        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.commerce_fragment, container, false);
        mBinding.newestProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.mostVisited.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.bestProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.recycleCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
       // setUpSlider();
        initListener();
        return mBinding.getRoot();
    }

    private void initListener() {
        mBinding.allNewest.setOnClickListener(view -> {
            Intent intent = AllProductActivity.newIntent(getActivity(), "date", " ");
            startActivity(intent);
        });

        mBinding.allMostvisited.setOnClickListener(view -> {
            Intent intent = AllProductActivity.newIntent(getActivity(), "popularity", " ");
            startActivity(intent);
        });

        mBinding.allBest.setOnClickListener(view -> {
            Intent intent = AllProductActivity.newIntent(getActivity(), "rating", " ");
            startActivity(intent);
        });
    }
/*
    private void setUpSlider() {
        HashMap<String, Integer> images = new HashMap<>();
        images.put("image1", R.drawable.image1);
        images.put("image2", R.drawable.image2);
        images.put("image3", R.drawable.image3);
        for (String imageName : images.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView.description(imageName).image(images.get(imageName)).setScaleType(BaseSliderView.ScaleType.CenterCrop);
            mBinding.sliderLayout.addSlider(textSliderView);
        }
    }*/




    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = AllProductActivity.newIntent(getContext(), "search", query);
                startActivity(intent);
                searchView.onActionViewCollapsed();
                return true;  // true if you want to handle code by self
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

        });
    }

    public void setUpAdapterCategory(List<CategoriesItem> categoryList) {
        if (isAdded()) {
            if (mAdapterCategory == null) {
                mAdapterCategory = new CategoryAdapter(getContext(), categoryList);
                mBinding.recycleCategory.setAdapter(mAdapterCategory);
                Log.d(TAG, "setUpAdapterCategory: adapterCATEGORYcall");
            }
            mAdapterCategory.setCategoriesItemList(categoryList);
            mAdapterCategory.notifyDataSetChanged();
            Log.d(TAG, "setUpAdapterCategory: hhhhhhh");
        }
    }

    public void setUpAdapterNewest(List<Response> responseList) {
        if (isAdded()) {
            if (mAdapterNewwst == null) {
                mAdapterNewwst = new ListAdapter(getContext(), responseList);
                mBinding.newestProduct.setAdapter(mAdapterNewwst);
            }
            mAdapterNewwst.setResponseList(responseList);
            mAdapterNewwst.notifyDataSetChanged();
            Log.d(TAG, "setUpAdapter: called");
        }
    }

    public void setUpAdapterMostVisited(List<Response> mostvisited) {
        if (isAdded()) {
            if (mAdapterMostViseted == null) {
                mAdapterMostViseted = new ListAdapter(getContext(), mostvisited);
                mBinding.mostVisited.setAdapter(mAdapterMostViseted);
            }
            mAdapterMostViseted.setResponseList(mostvisited);
            mAdapterMostViseted.notifyDataSetChanged();
            Log.d(TAG, "setUpAdapter: called");
        }
    }

    public void setUpAdapterBest(List<Response> best) {
        if (isAdded()) {
            if (mAdapterBest == null) {
                mAdapterBest = new ListAdapter(getContext(), best);
                mBinding.bestProduct.setAdapter(mAdapterBest);
            }
            mAdapterBest.setResponseList(best);
            mAdapterBest.notifyDataSetChanged();
            Log.d(TAG, "setUpAdapter: called");
        }
    }
}
