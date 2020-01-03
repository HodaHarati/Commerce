package com.example.commerce.view;


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
import com.example.commerce.adapter.CategoryAndSearchAdapter;
import com.example.commerce.adapter.ProductAdapter;
import com.example.commerce.databinding.CommerceFragmentBinding;
import com.example.commerce.databinding.FragmentCategoryPagerBinding;
import com.example.commerce.databinding.ItemSubcategoryBinding;
import com.example.commerce.model.CategoriesItem;
import com.example.commerce.model.Response;
import com.example.commerce.network.ProductRepository;
import com.example.commerce.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommerceFragment extends Fragment {

    private String TAG = "CommerceFragment";

    CommerceFragmentBinding mBinding;
    FragmentCategoryPagerBinding mCategoryPagerBinding;
    ProductViewModel mViewModel;
    ProductRepository productRepository;

    private ProductAdapter mAdapterNewwst;
    private ProductAdapter mAdapterMostViseted;
    private ProductAdapter mAdapterBest;
    private ProductAdapter mAdapterCategory;
    private CategoryAndSearchAdapter mSearchAdapter;
    private List<String> mCategoriesName = new ArrayList<>();
    private List<Response> mResponseList = new ArrayList<>();
    private searchCallbacks mSearchCallbacks;

    public static CommerceFragment newInstance() {

        Bundle args = new Bundle();

        CommerceFragment fragment = new CommerceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CommerceFragment() {
        // Required empty public constructor
    }

    public void setSearchCallbacks(searchCallbacks searchCallbacks) {
        mSearchCallbacks = searchCallbacks;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

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
        mViewModel.getAllCategories();

        mViewModel.getNewestProductLiveData().observe(this, responses -> {
            setUpAdapterNewest(responses);
            mResponseList.addAll(responses);
            // Log.d(TAG, "onChanged: " + responses);
        });
        mViewModel.getAllNewestProduct();

        mViewModel.getMostVisitedLiveData().observe(this, responses -> setUpAdapterMostvisited(responses));
        mViewModel.getMostVisitedProduct();

        mViewModel.getBestLiveData().observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(List<Response> responses) {
                setUpAdapterBest(responses);
            }
        });
        mViewModel.getBestProduct();

        productRepository = ProductRepository.getInstance();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.commerce_fragment, container, false);
        mBinding.executePendingBindings();

        mBinding.newestProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.mostVisited.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.bestProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.recycleCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        setUpSlider();
        return mBinding.getRoot();
    }

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
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchCallbacks.setUpAdapter(mViewModel.searchProduct(mResponseList,query));
                searchView.onActionViewCollapsed();
                return true;  // true if you want to handle code by self
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                mSearchCallbacks.setUpAdapter(mViewModel.searchProduct(mResponseList, newText));
                return true;
            }

        });
    }



    public void setUpAdapterCategory(List<CategoriesItem> categoryList) {
        if (isAdded()) {
            if (mAdapterCategory == null) {
                mAdapterCategory = new ProductAdapter(getContext(), categoryList);
                mBinding.recycleCategory.setAdapter(mAdapterCategory);
                Log.d(TAG, "setUpAdapterCategory: adapterCATEGORYcall");
            }
            mAdapterCategory.setItems(categoryList);
            mAdapterCategory.notifyDataSetChanged();
            Log.d(TAG, "setUpAdapterCategory: hhhhhhh");
        }
    }

    public void setUpAdapterNewest(List<Response> responseList) {
        if (isAdded()) {
            if (mAdapterNewwst == null) {
                mAdapterNewwst = new ProductAdapter(getContext(), responseList);
                mBinding.newestProduct.setAdapter(mAdapterNewwst);
            }
            mAdapterNewwst.setItems(responseList);
            mAdapterNewwst.notifyDataSetChanged();
            Log.d(TAG, "setUpAdapter: called");
        }
    }

    public void setUpAdapterMostvisited(List<Response> mostvisited) {
        if (isAdded()) {
            if (mAdapterMostViseted == null) {
                mAdapterMostViseted = new ProductAdapter(getContext(), mostvisited);
                mBinding.mostVisited.setAdapter(mAdapterMostViseted);
            }
            mAdapterMostViseted.setItems(mostvisited);
            mAdapterMostViseted.notifyDataSetChanged();
            Log.d(TAG, "setUpAdapter: called");
        }
    }

    public void setUpAdapterBest(List<Response> best) {
        if (isAdded()) {
            if (mAdapterBest == null) {
                mAdapterBest = new ProductAdapter(getContext(), best);
                mBinding.bestProduct.setAdapter(mAdapterBest);
            }
            mAdapterBest.setItems(best);
            mAdapterBest.notifyDataSetChanged();
            Log.d(TAG, "setUpAdapter: called");
        }
    }

    public interface searchCallbacks{
        void setUpAdapter(List<Response> listResponse);
    }
}
