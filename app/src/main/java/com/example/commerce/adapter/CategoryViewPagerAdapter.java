package com.example.commerce.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.commerce.model.product.CategoriesItem;
import com.example.commerce.view.category.CategoryPagerFragment;

import java.util.List;

public class CategoryViewPagerAdapter extends FragmentStatePagerAdapter {
    List<CategoriesItem> mCategoriesItems;

    public CategoryViewPagerAdapter(@NonNull FragmentManager fm, List<CategoriesItem> categoriesItems) {
        super(fm);
        mCategoriesItems = categoriesItems;
    }

    public void setCategoriesItems(List<CategoriesItem> categoriesItems) {
        mCategoriesItems = categoriesItems;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return CategoryPagerFragment.newInstance(mCategoriesItems.get(position).getId());
    }

    @Override
    public int getCount() {
        return mCategoriesItems.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoriesItems.get(position).getName();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
