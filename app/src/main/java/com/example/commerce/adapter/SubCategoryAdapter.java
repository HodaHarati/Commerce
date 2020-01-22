package com.example.commerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commerce.R;
import com.example.commerce.databinding.SubcategoryBinding;
import com.example.commerce.model.product.CategoriesItem;
import com.example.commerce.view.category.ProductOfSubcategoryActivity;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryHolder> {

    private Context mContext;
    private List<CategoriesItem> mCategoriesItemList;

    public SubCategoryAdapter(Context context, List<CategoriesItem> categoriesItemList) {
        mContext = context;
        mCategoriesItemList = categoriesItemList;
    }

    public void setCategoriesItemList(List<CategoriesItem> categoriesItemList) {
        mCategoriesItemList = categoriesItemList;
    }

    @NonNull
    @Override
    public SubCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SubcategoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.subcategory, parent, false);
        return new SubCategoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryHolder holder, int position) {
        holder.bind(mCategoriesItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategoriesItemList.size();
    }

    public class SubCategoryHolder extends RecyclerView.ViewHolder {

        private CategoriesItem mCategoriesItem;
        private SubcategoryBinding binding;

        public SubCategoryHolder(SubcategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.txtSubcategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = ProductOfSubcategoryActivity.newIntent(mContext, mCategoriesItem.getId());
                    mContext.startActivity(intent);
                }
            });

        }

        public void bind(CategoriesItem categoriesItem) {
            mCategoriesItem = categoriesItem;
            binding.txtSubcategory.setText(mCategoriesItem.getName());
        }
    }
}
