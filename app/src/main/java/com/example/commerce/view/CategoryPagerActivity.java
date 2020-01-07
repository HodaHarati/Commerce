package com.example.commerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.commerce.R;
import com.example.commerce.adapter.CategoryViewPagerAdapter;
import com.example.commerce.databinding.ActivityCategoryPagerBinding;
import com.example.commerce.model.CategoriesItem;
import com.example.commerce.viewmodel.ProductViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CategoryPagerActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_ID = "Category_id";
    private ActivityCategoryPagerBinding mBinding;
    private ProductViewModel mViewModel;
    private int mCategoryid;
    CategoryViewPagerAdapter mCategoryViewPagerAdapter;

    public static Intent newIntent(Context context, int categoryId) {
        Intent intent = new Intent(context, CategoryPagerActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_category_pager);
        mBinding.executePendingBindings();
        mCategoryid = getIntent().getIntExtra(EXTRA_CATEGORY_ID, 0);
        mBinding.tablayout.setupWithViewPager(mBinding.viewPager);

        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mViewModel.getAllCategoriesLiveData().observe(this, categoriesItems -> setUpViewPager(categoriesItems));
       // mViewModel.getAllCategories();  // causes crash

/*        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_fragment_category_pager);
        if (fragment == null)
            getSupportFragmentManager().beginTransaction()
                                        .add(R.id.container_fragment_category_pager, CategoryPagerFragment.newInstance(mCategoryid))
                                        .commit();*/
    }

    public void setUpViewPager(List<CategoriesItem> categoriesItems) {
        if (mCategoryViewPagerAdapter == null) {
            mCategoryViewPagerAdapter = new CategoryViewPagerAdapter(getSupportFragmentManager(), categoriesItems);
            mBinding.viewPager.setAdapter(mCategoryViewPagerAdapter);
//            mBinding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mBinding.tablayout));
        }else {
            mCategoryViewPagerAdapter.setCategoriesItems(categoriesItems);
            mCategoryViewPagerAdapter.notifyDataSetChanged();
        }
    }
}
