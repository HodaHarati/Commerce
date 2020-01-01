package com.example.commerce.view;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commerce.R;
import com.example.commerce.databinding.FragmentCategoryPagerBinding;
import com.example.commerce.databinding.ItemSubcategoryBinding;
import com.example.commerce.model.Response;
import com.example.commerce.network.ProductRepository;
import com.example.commerce.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryPagerFragment extends Fragment { //implements ProductRepository.ProductRepositoryCallbackListproductInCategory {

    private String TAG = "CategoryPagerFragment";

    public static final String ARG_CATEGORY_ID = "position";
    private FragmentCategoryPagerBinding mBinding;
    private int mCategoryId;
    private ProductViewModel mViewModel;
    private SubcategoryAdapter mSubCategoryAdapter;
    /*private ProductRepository mProductRepository;
    private List<Response> mListProductInCategory;*/

    public static CategoryPagerFragment newInstance(int categoryId) {
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY_ID, categoryId);
        CategoryPagerFragment fragment = new CategoryPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = getArguments().getInt(ARG_CATEGORY_ID);
        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mViewModel.getSubCategoryLiveData().observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(List<Response> responses) {
                setUpSubCategoryAdapter(responses);
            }
        });
        mViewModel.getListProductInCategoriy(mCategoryId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_pager, container, false);
        mBinding.executePendingBindings();
        mBinding.subcategoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return mBinding.getRoot();
    }
    public void setUpSubCategoryAdapter(List<Response> listLiveData) {
        /*mProductRepository = ProductRepository.getInstance();
        mProductRepository.getListProductInCategoriy(mCategoryId);
        mProductRepository.setCallbackListproductInCategory(this);*/
        if (isAdded()) {
            if (mSubCategoryAdapter == null) {
                mSubCategoryAdapter = new SubcategoryAdapter(listLiveData);
                mBinding.subcategoryRecycler.setAdapter(mSubCategoryAdapter);

            }
            mSubCategoryAdapter.setResponseList(listLiveData);
            mSubCategoryAdapter.notifyDataSetChanged();

        }
    }

    private class SubCategoryHolder extends RecyclerView.ViewHolder {
        ItemSubcategoryBinding mItemSubcategoryBinding;
        Response mSubCategoriesItem;

        public SubCategoryHolder(@NonNull ItemSubcategoryBinding itemView) {
            super(itemView.getRoot());
            mItemSubcategoryBinding = itemView;

            // mItemSubcategoryBinding.cardSubcategory.setOnCli;

        }
        public void bind(Response categoriesItem) {
            mSubCategoriesItem = categoriesItem;
            mItemSubcategoryBinding.txtSubcategory.setText(mSubCategoriesItem.getName());
            Picasso.with(getContext())
                    .load(mSubCategoriesItem.getImages().get(0).getSrc())
                    .placeholder(R.drawable.image_loading)
                    .into(mItemSubcategoryBinding.imgSubcategory);
        }
    }

    private class SubcategoryAdapter extends RecyclerView.Adapter<SubCategoryHolder> {

        List<Response> mResponseList;

        public SubcategoryAdapter(List<Response> categoriesItemList) {
            mResponseList = categoriesItemList;
        }

        public void setResponseList(List<Response> responseList) {
            mResponseList = responseList;
        }

        @NonNull
        @Override
        public SubCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemSubcategoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.item_subcategory, parent, false);
            return new SubCategoryHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SubCategoryHolder holder, int position) {
            holder.bind(mResponseList.get(position));
        }

        @Override
        public int getItemCount() {
            return mResponseList.size();
        }
    }
/*
    @Override
    public void onResponse(List<Response> productInCategory) {
        mListProductInCategory = productInCategory;
        setUpSubCategoryAdapter();
    }*/
}
