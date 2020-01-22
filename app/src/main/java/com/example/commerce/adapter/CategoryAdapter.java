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
import com.example.commerce.databinding.CategoryListBinding;
import com.example.commerce.model.product.CategoriesItem;
import com.example.commerce.view.category.CategoryPagerActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private Context mContext;
    private List<CategoriesItem> mCategoriesItemList;

    public CategoryAdapter(Context context, List<CategoriesItem> categoriesItemList) {
        mContext = context;
        mCategoriesItemList = categoriesItemList;
    }

    public void setCategoriesItemList(List<CategoriesItem> categoriesItemList) {
        mCategoriesItemList = categoriesItemList;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.category_list, parent, false);
        return new CategoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.bind(mCategoriesItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategoriesItemList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        private CategoriesItem mCategoriesItem;
        private CategoryListBinding binding;

        public CategoryHolder(CategoryListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.btnCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = CategoryPagerActivity.newIntent(mContext, mCategoriesItem.getId());
                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(CategoriesItem categoriesItem) {
            mCategoriesItem = categoriesItem;
            binding.btnCategory.setText(mCategoriesItem.getName());
        }
    }

}
