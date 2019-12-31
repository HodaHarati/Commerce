package com.example.commerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.commerce.R;
import com.example.commerce.adapter.CategoryViewPagerAdapter;
import com.example.commerce.databinding.ActivityCategoryPagerBinding;
import com.example.commerce.model.CategoriesItem;
import com.example.commerce.viewmodel.ProductViewModel;

import java.util.List;

public class CategoryPagerActivity extends AppCompatActivity {

    //   public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    private ActivityCategoryPagerBinding mBinding;
    private ProductViewModel mViewModel;
    CategoryViewPagerAdapter mCategoryViewPagerAdapter;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CategoryPagerActivity.class);
        //intent.putStringArrayListExtra(EXTRA_CATEGORY_NAME, (ArrayList<String>) categoryName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_category_pager);
        mBinding.executePendingBindings();
        // mCategoriesName.addAll(getIntent().getStringArrayListExtra(EXTRA_CATEGORY_NAME));
        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mViewModel.getAllCategoriesLiveData().observe(this, new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoriesItems) {
                setUpViewPager(categoriesItems);
            }
        });
    }

    public void setUpViewPager(List<CategoriesItem> categoriesItems) {
        mCategoryViewPagerAdapter = new CategoryViewPagerAdapter(getSupportFragmentManager(), categoriesItems);
        mBinding.viewPager.setAdapter(mCategoryViewPagerAdapter);
        mBinding.tablayout.setupWithViewPager(mBinding.viewPager);

        //mCategoryViewPagerAdapter.notifyDataSetChanged();
    }
}
