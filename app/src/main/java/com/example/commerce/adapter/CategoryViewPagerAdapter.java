package com.example.commerce.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.commerce.model.CategoriesItem;
import com.example.commerce.view.CategoryPagerFragment;

import java.util.List;

public class CategoryViewPagerAdapter extends FragmentPagerAdapter {
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
}
